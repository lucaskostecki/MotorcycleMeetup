package controller;

import entity.Route;
import entity.User;
import persistence.GenericDao;

public class RouteManager {

    public boolean getUserPrivileges(int routeID, String username) {
        GenericDao dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(routeID);

        dao = new GenericDao(User.class);
        User targetUser = (User) dao.getByPropertyLike("username", username).get(0);

        for (Route route : targetUser.getRoutes()) {
            if (targetRoute.getRouteID() == route.getRouteID()) {
                return true;
            }
        }

        return false;
    }

}
