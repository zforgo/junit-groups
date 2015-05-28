package hu.zforgo.resteasy;

import hu.zforgo.resteasy.model.request.ExampleDTO;
import hu.zforgo.resteasy.model.request.NotWorkingDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample4")
public class Example04RestService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response bodyRead(@QueryParam("xml") NotWorkingDTO dto) {
		return Response.ok("Body found: " + dto.getName()).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/works")
	public Response workingBodyRead(@QueryParam("xml") ExampleDTO dto) {
		return Response.ok("Body found: " + dto.getRequestId()).build();
	}
}
