package app.userauth.services.utils;

import app.filerepository.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import app.userauth.model.UserEntity;
import app.userauth.repo.DBUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static app.userauth.encription.PasswordEncode.passwordEncoder;

public class AuthUtils {
    private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);

    public static ResponseMessage checkValidity(UserEntity userDetails, LoginRequest loginRequest) {
        if(userDetails == null ||
                !passwordEncoder().matches(loginRequest.getPassword(), userDetails.getPassword())){
            logger.warn("Invalid name or password!");
            return ResponseMessage.getInstance("Invalid name or password!");
        }
        logger.info("SignIn has done Successfully");
        return ResponseMessage.getInstance("SignIn has done Successfully");
    }

}
