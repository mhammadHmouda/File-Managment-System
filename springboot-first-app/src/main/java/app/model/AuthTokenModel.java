package app.model;
public class AuthTokenModel {
    private String token;
    private String role;
    private String status;

    public AuthTokenModel(){

    }

    public AuthTokenModel(String token, String role){
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}