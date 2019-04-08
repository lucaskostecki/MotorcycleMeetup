package controller;

import entity.User;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/users")
public class GetUsersREST {
    @GET
    @Path("/{username}")
    @Produces("application/json")
    public Response getUser(@PathParam("username") String username) {
        GenericDao dao = new GenericDao(User.class);
        CrunchifyToJSON gson = new CrunchifyToJSON();

        String json = gson.listToJSON(dao.getByPropertyLike("username", username));

        return Response.status(200).entity(json).build();
    }
}
