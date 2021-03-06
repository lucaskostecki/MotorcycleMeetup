package entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The POJO for waypoints
 */
@Entity(name="Waypoint")
@Table(name="Waypoints")
public class Waypoint {

    @Getter
    @Setter
    @Id
    @Expose()
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int waypointID;

    @Getter
    @Setter
    @Expose()
    @Column(name="WaypointName")
    private String waypointName;

    @Getter
    @Setter
    @ManyToOne
    @Expose(serialize = false, deserialize = false)
    @JoinColumn(name = "RouteID")
    private Route route;

    public Waypoint () {}

    public Waypoint(String waypointName, Route route) {
        this.waypointName = waypointName;
        this.route = route;
    }
}