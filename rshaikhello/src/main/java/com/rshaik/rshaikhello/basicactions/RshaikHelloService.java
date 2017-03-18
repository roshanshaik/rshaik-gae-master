
package com.rshaik.rshaikhello.basicactions;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * RshaikHelloService accepts a rest api POST to the URI /api/hello taking in a query parameter of name=<some name>
 * 
 * /api is the root of the API Service for Jersey Servlet Container
 * 
 * @author rshaik
 *
 */

@Path("/hello")
public class RshaikHelloService {

	private static final Logger logger = Logger.getLogger(RshaikHelloService.class.getName());

	

	@Path("/")
	@POST
	@Produces("application/json")
	public Response sayHello(@QueryParam("name") String name, @Context UriInfo uriInfo) throws JSONException {

		logger.log(Level.INFO, "doGet uriInfo is" + uriInfo);
		String query = uriInfo.getRequestUri().getQuery();
		logger.log(Level.INFO, "doGet query is"+query);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", name); 

		logger.log(Level.INFO, "doGet Request is"+ jsonObject.toString());


		return Response.status(200).entity(jsonObject.toString()).build();
	}

}