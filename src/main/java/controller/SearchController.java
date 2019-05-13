package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Response;
import entity.Route;
import entity.ZipCodesItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/search"}
)

public class SearchController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao dao;

        String search = request.getParameter("q");

        if (search != null) {
            if (search.matches("^[0-9]{5}$")) {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                try (InputStream input = loader.getResourceAsStream("api.properties")) {
                    Properties properties = new Properties();
                    properties.load(input);

                    // Make request to api
                    logger.debug("REQUESTING ZIPS");
                    Client client = ClientBuilder.newClient();
                    WebTarget target =
                            client.target("https://www.zipcodeapi.com/rest/" + properties.getProperty("zipcodeapi") + "/radius.json/" + search + "/15/mile");
                    String ajaxResp = target.request(MediaType.APPLICATION_JSON).get(String.class);

                    // Convert request into objects
                    ObjectMapper mapper = new ObjectMapper();
                    Response objResp = mapper.readValue(ajaxResp, Response.class);
                    List<ZipCodesItem> zci = objResp.getZipCodes();

                    dao = new GenericDao(Route.class);
                    List<Route> routesByZip = new ArrayList<>();

                    // Set the routes that match searched zip code
                    request.getSession().setAttribute("routes", dao.getByPropertyLike("zipCode", search));

                    for (ZipCodesItem item : zci) {
                        logger.debug("CURRENT ZIP " + item);
                        String currentZip = item.getZipCode();

                        List<Route> routes = dao.getByPropertyLike("zipCode", currentZip);
                        for (Route route : routes) {
                            routesByZip.add(route);
                        }
                    }

                    request.getSession().setAttribute("routesByZipCode", routesByZip);
                } catch (IOException e) {
                    logger.error("Could not load properties: " + e);
                }
            } else {
                dao = new GenericDao(Route.class);
                request.getSession().setAttribute("routes", dao.getByPropertyLike("title", search));
            }
        }

        request.getSession().setAttribute("search", search);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchresults.jsp");
        dispatcher.forward(request, response);
    }

}
