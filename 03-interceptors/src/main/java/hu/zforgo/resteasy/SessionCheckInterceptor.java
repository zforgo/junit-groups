package hu.zforgo.resteasy;

import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
@SecurityPrecedence
@RequestScoped
public class SessionCheckInterceptor implements PreProcessInterceptor, AcceptedByMethod {

	@Context
	HttpServletRequest servletRequest;

	@Override
	public boolean accept(Class declaring, Method method) {
		return !(method.isAnnotationPresent(PublicAccess.class) || declaring.isAnnotationPresent(PublicAccess.class));
	}

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		String sid = servletRequest.getParameter("sid");
		if (!"VALID_SID".equals(sid)) {
			return ServerResponse.copyIfNotServerResponse(
					Response.status(Response.Status.FORBIDDEN).entity("Ne hekkelj, hallod?!").build()
			);
		}
		return null;
	}
}
