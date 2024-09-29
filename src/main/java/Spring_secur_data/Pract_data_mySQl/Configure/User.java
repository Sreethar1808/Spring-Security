package Spring_secur_data.Pract_data_mySQl.Configure;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "student")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

    public User(UUID id, String username, String password, String roll_no) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roll_no;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roll_no) {
        this.roles = roll_no;
    }
}
