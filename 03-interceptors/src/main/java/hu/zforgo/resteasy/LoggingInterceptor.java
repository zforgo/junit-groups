package hu.zforgo.resteasy;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Provider
@RequestScoped
public class LoggingInterceptor implements PreProcessInterceptor, PostProcessInterceptor, AcceptedByMethod {

	public static final String X_REQUEST_ID = "X-requestId";
	@Context
	HttpServletRequest req;

	@Inject
	RequestLogger logger;

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		req.setAttribute(X_REQUEST_ID, "redId_" + System.currentTimeMillis());
		return null;
	}

	@Override
	public void postProcess(ServerResponse response) {
		final String reqId = (String) req.getAttribute(X_REQUEST_ID);
		long arrived = (Long) req.getAttribute(AlwaysRunInterceptor.X_ARRIVED);
		try {
			Object obj = response.getEntity();
			String path = logger.logResponse(obj, reqId, arrived);
			MultivaluedMap<String, Object> headers = response.getMetadata();
			List<Object> logs = headers.get("X-LogFile");
			if (logs == null) {
				logs = new ArrayList<Object>();
			}
			logs.add(path);
			headers.put("X-LogFile", logs);
		} finally {
			req.removeAttribute(X_REQUEST_ID);
		}
	}

	@Override
	public boolean accept(Class declaring, Method method) {
		return method.isAnnotationPresent(Logged.class) || declaring.isAnnotationPresent(Logged.class);
	}
}
