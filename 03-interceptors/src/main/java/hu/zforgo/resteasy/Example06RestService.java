package hu.zforgo.resteasy;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/sample6")
public class Example06RestService {

	@Context
	HttpServletRequest request;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response intercepted(@HeaderParam("X-Arrived") long requestArrived) {
		return Response.ok("Request arrived at: " + new Date(requestArrived)).build();
	}

	@PublicAccess
	@GET
	@Path("/public")
	public Response publicAccess() {
		return Response.ok("Access is always granted").build();
	}
}
