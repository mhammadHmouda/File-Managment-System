package app.userauth.services.signinservice.intf;
import app.filerepository.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ISignInService {
    ResponseMessage signIn (LoginRequest loginRequest);
}
