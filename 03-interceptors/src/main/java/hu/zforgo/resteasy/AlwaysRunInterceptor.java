package hu.zforgo.resteasy;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
@RequestScoped
public class AlwaysRunInterceptor implements PreProcessInterceptor {

	@Context
	HttpHeaders headers;

	public static final String X_ARRIVED = "X-Arrived";

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		long systime = System.currentTimeMillis();
		request.setAttribute(X_ARRIVED, systime);
		request.getHttpHeaders().getRequestHeaders().add(X_ARRIVED, String.valueOf(systime));
		return null;
	}
}
