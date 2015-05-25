package hu.zforgo.resteasy;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Example01RestService {

	@GET
	public Response base(){
		return Response.noContent().build();
	}

	@GET
	@Path("/extra")
	public Response extraPath() {
		return Response.noContent().build();
	}

	@GET
	@Path("/query")
	@Produces(MediaType.TEXT_PLAIN)
	public Response queryParams(@QueryParam("foo") String foo, @QueryParam("bar") @DefaultValue("100") int bar) {
		return Response.ok(String.format("parameters parsed: [foo]: %s, [bar] %d", foo, bar)).build();
	}

	@GET
	@Path("/path/{foo}/param/{bar}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response pathParams(@PathParam("foo") String foo, @PathParam("bar") @DefaultValue("250") int bar) {
		return Response.ok(String.format("path parsed: [foo]: %s, [bar] %d", foo, bar)).build();
	}


	@Path("/header")
	@Produces(MediaType.TEXT_PLAIN)
	public Response headerParam(@HeaderParam("X-Passed") boolean passed) {
		return Response.ok(passed? "MEGFELETEM" : "MEGBUKTAM").build();
	}
}