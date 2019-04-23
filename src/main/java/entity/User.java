package entity;

import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "Password")
    private String password;

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
     * @param password  the password
     */
    public User (int type, String username, String email, String phone, String firstName, String lastName, String password) {
        this.type = type;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}