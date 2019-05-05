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

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native",strategy="native")
    private int userID;

    @Getter
    @Setter
    @Column(name="Type")
    private int type;

    @Getter
    @Setter
    @Column(name="Username")
    private String username;

    @Getter
    @Setter
    @Column(name="Email")
    private String email;

    @Getter
    @Setter
    @Column(name="Phone")
    private String phone;

    @Getter
    @Setter
    @Column(name="FirstName")
    private String firstName;

    @Getter
    @Setter
    @Column(name="LastName")
    private String lastName;

    @Getter
    @Setter
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