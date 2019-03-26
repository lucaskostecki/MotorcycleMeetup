package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The POJO for roles
 */
@Entity(name="Role")
@Table(name="Roles")
public class Role {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int roleID;

    @Getter
    @Setter
    @Column(name="RoleID")
    private String roleName;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    /**
     * Instantiates a new Role.
     */
    public Role() {}

    /**
     * Instantiates a new Role.
     *
     * @param roleName the role name
     * @param user     the user
     */
    public Role(String roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }
}
