package entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Expose()
    @Column(name = "DateAdded")
    private Date dateAdded;

    @Getter
    @Setter
    @Expose()
    @Column(name = "AvoidHighways")
    private boolean avoidHighways;

    @Getter
    @Setter
    @Expose()
    @Column(name = "StartTime")
    private String startTime;

    @Getter
    @Setter
    @Expose()
    @Column(name = "StartDate")
    private java.util.Date startDate;

    @Getter
    @Setter
    @Expose()
    @Column(name = "Public")
    private boolean publicRide;

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
     * @param start       the start
     * @param end         the end
     * @param title       the title
     * @param description the description
     * @param user        the user
     */
    public Route(String start, String end, String title, String description, boolean avoidHighways,
                 java.util.Date startDate, String startTime, boolean publicRide, User user) {
        this.start = start;
        this.end = end;
        this.startDate = startDate;
        this.startTime = startTime;
        this.title = title;
        this.description = description;
        this.avoidHighways = avoidHighways;
        this.publicRide = publicRide;
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
                ", dateAdded=" + dateAdded +
                ", avoidHighways=" + avoidHighways +
                ", startTime='" + startTime + '\'' +
                ", startDate=" + startDate +
                ", publicRide=" + publicRide +
                '}';
    }
}