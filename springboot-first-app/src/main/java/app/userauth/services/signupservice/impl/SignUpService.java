package app.userauth.services.signupservice.impl;

import app.response.ResponseMessage;
import app.userauth.controller.SignUp;
import app.userauth.model.UserEntity;
import app.userauth.repo.DBUserRepository;
import app.userauth.services.signupservice.intf.ISignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static app.userauth.encription.PasswordEncode.passwordEncoder;
@Service
public class SignUpService implements ISignUpService {
    @Autowired
    DBUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignUp.class);


    public ResponseMessage signUp(UserEntity user) {
        try {
            logger.info("Sign up started");
            UserEntity userDetails = userRepository.findByUsername(user.getUsername());
            if (userDetails != null) {
                logger.warn("user already exist");
                return ResponseMessage.getInstance("User already exist");
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            logger.info("Sign up done successfully");
            return ResponseMessage.getInstance("Sign up done successfully");
        } catch (Exception e) {
            logger.error("Error in signUp");
            return ResponseMessage.getInstance("Something wrong!!, try again");
        }
    }
}


