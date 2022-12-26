package app.userauth.signin.service.intf;
import app.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface ISignInService {
    ResponseMessage signIn (LoginRequest loginRequest) throws Exception;
}
