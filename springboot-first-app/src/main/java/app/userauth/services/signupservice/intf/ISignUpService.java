package app.userauth.services.signupservice.intf;

import app.userauth.model.UserEntity;
import org.springframework.http.ResponseEntity;

public interface ISignUpService {
    ResponseEntity<?> signUp(UserEntity user);
}
