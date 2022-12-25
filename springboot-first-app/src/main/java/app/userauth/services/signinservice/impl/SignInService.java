package app.userauth.services.signinservice.impl;

import app.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import app.userauth.model.UserEntity;
import app.userauth.repo.DBUserRepository;
import app.userauth.services.signinservice.intf.ISignInService;
import app.userauth.services.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService implements ISignInService {
    @Autowired
    DBUserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(SignInService.class);

    public ResponseMessage signIn(LoginRequest loginRequest){
        try {
            logger.info("SignIn started");

            UserEntity userDetails = userRepository.findByUsername(loginRequest.getUsername());

            return AuthUtils.checkValidity(userDetails, loginRequest);

        } catch (Exception e) {
            logger.error("Failed signIn");
            return ResponseMessage.getInstance("Failed to sign in!! , try again");
        }
    }


}
