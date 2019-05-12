package entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The POJO for waypoints
 */
@Entity(name="Invite")
@Table(name="Invites")
public class Invite {

    @Getter
    @Setter
    @Id
    @Expose()
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int inviteID;

    @Getter
    @Setter
    @Expose()
    @Column(name="Email")
    private String email;

    @Getter
    @Setter
    @Expose()
    @Column(name = "Pending")
    private boolean pending;

    @Getter
    @Setter
    @ManyToOne
    @Expose(serialize = false, deserialize = false)
    @JoinColumn(name = "RouteID")
    private Route route;

    public Invite() {}

    public Invite(String email, boolean pending, Route route) {
        this.email = email;
        this.pending = pending;
        this.route = route;
    }
}