package controller;

import entity.Route;
import entity.User;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/getridesaslist")
public class GetRidesAsList {
    @GET
    @Path("/{username}")
    @Produces("application/json")
    public Response getUserRoutes(@PathParam("username") String username) {
        GenericDao dao;
        dao = new GenericDao(User.class);
        CrunchifyToJSON gson = new CrunchifyToJSON();

        User targetUser = (User)dao.getByPropertyLike("username", username).get(0);
        dao = new GenericDao(Route.class);

        String json = gson.listToJSON(dao.getByLinkingIDLike("user", targetUser.getUserID()));

        return Response.status(200).entity(json).build();
    }
}
