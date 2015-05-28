package hu.zforgo.resteasy;


import hu.zforgo.resteasy.model.SampleBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample7")
public class Example07RestService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response logged() {
		return Response.ok("String based response").build();
	}

	@GET
	@Path("/object")
	@Produces(MediaType.APPLICATION_JSON)
	@Logged
	public Response validObject() {
		SampleBody res = new SampleBody();
		res.setBar("Ezegybar");
		res.setFoo(978654);
		res.setName("Egykerek aki nekimegy valaminek");
		return Response.ok(res).build();
	}

	@GET
	@Path("/exception")
	@Produces(MediaType.APPLICATION_JSON)
	@Logged
	public Response invalidObject(@QueryParam("name") String name) throws InvalidInputException {
		if (Utils.isEmpty(name)) {
			throw new InvalidInputException("Parameter [name] is missing");
		}
		SampleBody res = new SampleBody();
		res.setBar("Ezegybar");
		res.setFoo(978654);
		res.setName("Egykerek aki nekimegy valaminek");
		return Response.ok(res).build();
	}
}
