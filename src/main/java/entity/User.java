package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The user object POJO
 */
@Entity(name="User")
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int userID;

    @Column(name="Type")
    private int type;

    @Column(name="Username")
    private String username;

    @Column(name="Email")
    private String email;

    @Column(name="Phone")
    private String phone;

    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Route> routes = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User () {}

    /**
     * Instantiates a new User.
     *
     * @param type      the type
     * @param username  the username
     * @param email     the email
     * @param phone     the phone
     * @param firstName the first name
     * @param lastName  the last name
     */
    public User (int type, String username, String email, String phone, String firstName, String lastName) {
        this.type = type;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets routes.
     *
     * @return the routes
     */
    public Set<Route> getRoutes() {
        return routes;
    }

    /**
     * Sets routes.
     *
     * @param routes the routes
     */
    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    /**
     * Add route.
     *
     * @param route the route
     */
    public void addRoute(Route route) {
        routes.add(route);
        route.setUser(this);
    }

    /**
     * Remove route.
     *
     * @param route the route
     */
    public void removeRoute(Route route) {
        routes.remove(route);
        route.setUser(null);
    }
}