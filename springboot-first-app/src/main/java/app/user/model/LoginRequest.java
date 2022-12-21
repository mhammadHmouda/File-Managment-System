package app.user.model;

import app.filerepository.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRequest {
    private String username;
    private static final Logger logger = LoggerFactory.getLogger(LoginRequest.class);
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

    private String password;
}
