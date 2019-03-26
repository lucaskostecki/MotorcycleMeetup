package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

/**
 * The POJO for routes
 */
@Entity(name="Route")
@Table(name="Routes")
public class Route {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int routeID;

    @Getter
    @Setter
    @Column(name="Title")
    private String title;

    @Getter
    @Setter
    @Column(name="Description")
    private String description;

    @Getter
    @Setter
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
}