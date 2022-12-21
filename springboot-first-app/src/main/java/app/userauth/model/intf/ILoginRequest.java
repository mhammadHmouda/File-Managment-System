package app.userauth.model.intf;

public interface ILoginRequest {
    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);
}
