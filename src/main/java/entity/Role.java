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

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int roleID;

    @Column(name="RoleName")
    private String roleName;

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


    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
