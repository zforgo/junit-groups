package hu.zforgo.resteasy;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

@Provider
@RequestScoped
public class AlwaysRunInterceptor implements PreProcessInterceptor {

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		request.getHttpHeaders().getRequestHeaders().add("X-Arrived", String.valueOf(System.currentTimeMillis()));
		return null;
	}
}
