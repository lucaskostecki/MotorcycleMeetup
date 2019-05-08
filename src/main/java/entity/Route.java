package entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The POJO for routes
 */
@Entity(name="Route")
@Table(name="Routes")
public class Route {

    @Getter
    @Setter
    @Id
    @Expose()
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int routeID;

    @Getter
    @Setter
    @Expose()
    @Column(name = "Start")
    private String start;

    @Getter
    @Setter
    @Expose()
    @Column(name = "End")
    private String end;

    @Getter
    @Setter
    @Expose()
    @Column(name="Title")
    private String title;

    @Getter
    @Setter
    @Expose()
    @Column(name="Description")
    private String description;

    @Getter
    @Setter
    @Expose(serialize = false, deserialize = false)
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Getter
    @Setter
    @Expose(serialize = false, deserialize = false)
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Waypoint> waypoints = new HashSet<>();


    /**
     * Instantiates a new Route.
     */
    public Route() {}

    /**
     * Instantiates a new Route.
     *
     * @param title       the title
     * @param description the description
     * @param user        the user
     */
    public Route(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    /**
     * Instantiates a new Route.
     *
     * @param start       the start
     * @param end         the end
     * @param title       the title
     * @param description the description
     * @param user        the user
     */
    public Route(String start, String end, String title, String description, User user) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}