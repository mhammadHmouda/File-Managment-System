package app.userauth.signup.service.impl;

import app.exception.AuthException;
import app.response.ResponseMessage;
import app.userauth.signup.controller.SignUp;
import app.userauth.model.UserEntity;
import app.userauth.repo.DBUserRepository;
import app.userauth.signup.service.intf.ISignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static app.userauth.encription.PasswordEncode.passwordEncoder;
import static app.userauth.signin.constant.ConstantAuth.*;

@Service
public class SignUpService implements ISignUpService {
    @Autowired
    DBUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignUp.class);

    public ResponseMessage signUp(UserEntity user) throws AuthException {
        try {
            logger.info("Sign up started");
            UserEntity userDetails = userRepository.findByUsername(user.getUsername());
            if (userDetails != null) {
                logger.warn("user already exist");
                return ResponseMessage.getInstance(USER_EXIST);
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            logger.info("Sign up done successfully");
            return ResponseMessage.getInstance(SUCCESS_SIGN_UP);
        } catch (Exception e) {
            logger.error("Error in signUp");
            throw new AuthException(INVALID_AUTH);
        }
    }
}


