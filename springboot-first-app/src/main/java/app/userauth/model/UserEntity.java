package app.userauth.model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import static app.userauth.model.Constant.*;

@Entity
@Table(name = TABLE_NAME)
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = ID_FIELD)
    private int id;
    @Column(name = USER_NAME_FIELD)
    private String username;
    @Column(name = PASSWORD_FIELD)
    private String password;
    @Column(name = ROLE_FIELD)
    private String role;
    private static final Logger logger = LoggerFactory.getLogger(UserEntity.class);
    public UserEntity() {
        logger.info("UserEntity has been created");
    }


    public UserEntity( String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        logger.info(username+" user created");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
