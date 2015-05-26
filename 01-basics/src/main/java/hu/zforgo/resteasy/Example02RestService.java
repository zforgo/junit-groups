package hu.zforgo.resteasy;

import hu.zforgo.resteasy.model.SampleBody;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Example02RestService {

	@Path("/body")
	@POST
	public Response entity(SampleBody body) {
		body.setFoo(-body.getFoo());
		return Response.ok(body).build();
	}

	@Path("/body/{name}")
	@POST
	public Response mixed(SampleBody body, @PathParam("name") String name, @QueryParam("foo") Integer newFoo, @HeaderParam("X-Bar") String bar) {
		if (newFoo != null) {
			body.setFoo(newFoo);
		}
		body.setName(name);
		if (bar != null) {
			body.setBar(bar);
		}
		return Response.ok(body).build();
	}

	@GET
	@Path("/entity")
	public SampleBody entityResponse() {
		SampleBody res = new SampleBody();
		res.setFoo(12);
		res.setBar("Bar, ez csak egy bar");
		res.setName("Sample Name");
		return res;
	}
}