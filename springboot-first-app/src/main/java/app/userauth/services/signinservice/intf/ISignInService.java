package app.userauth.services.signinservice.intf;
import app.userauth.model.intf.ILoginRequest;
import org.springframework.http.ResponseEntity;

public interface ISignInService {
    ResponseEntity<?> signIn (ILoginRequest loginRequest);
}
