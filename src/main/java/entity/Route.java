package entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

/**
 * The POJO for routes
 */
@Entity(name="Route")
@Table(name="Routes")
public class Route {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int routeID;

    @Column(name="Title")
    private String title;

    @Column(name="Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;


    /**
     * Instantiates a new Route.
     */
    public Route() {}

    /**
     * Instantiates a new Route.
     *
     * @param title       the title
     * @param description the description of the route
     * @param user        the user to be tied with the route
     */
    public Route(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets route id.
     *
     * @return the route id
     */
    public int getRouteID() {
        return routeID;
    }

    /**
     * Sets route id.
     *
     * @param routeID the route id
     */
    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
}