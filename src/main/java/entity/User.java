package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

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

    public User () {}

    public User (int type, String username, String email, String phone, String firstName, String lastName) {
        this.type = type;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}