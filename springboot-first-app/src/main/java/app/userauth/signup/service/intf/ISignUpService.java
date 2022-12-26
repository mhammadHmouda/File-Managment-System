package app.userauth.signup.service.intf;

import app.exception.AuthException;
import app.response.ResponseMessage;
import app.userauth.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface ISignUpService {
    ResponseMessage signUp(UserEntity user) throws AuthException;
}
