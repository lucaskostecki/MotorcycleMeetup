package entity;

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

    @Column(name="RoleID")
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

    /**
     * Gets role id.
     *
     * @return the role id
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * Sets role id.
     *
     * @param roleID the role id
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
