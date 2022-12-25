package app.userauth.services.signupservice.intf;

import app.filerepository.response.ResponseMessage;
import app.userauth.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface ISignUpService {
    ResponseMessage signUp(UserEntity user);
}
