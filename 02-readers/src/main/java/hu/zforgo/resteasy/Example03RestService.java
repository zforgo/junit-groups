package hu.zforgo.resteasy;

import hu.zforgo.resteasy.model.request.ExampleDTO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample3")
public class Example03RestService {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response bodyRead(ExampleDTO dto) {
		return Response.ok("Body found: " + dto.getRequestId()).build();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/string")
	public Response bodyRead(Object dto) {
		return Response.ok("Body found: " + dto).build();
	}
}
