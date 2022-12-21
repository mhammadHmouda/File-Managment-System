package app.userauth.services.signupservice.impl;

import app.userauth.controller.SignUp;
import app.userauth.model.UserEntity;
import app.userauth.repo.DBUserRepository;
import app.userauth.services.signupservice.intf.ISignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import static app.userauth.encription.PasswordEncode.passwordEncoder;
@Service
public class SignUpService implements ISignUpService {
    @Autowired
    DBUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignUp.class);


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp( UserEntity user) {
        try {
            logger.info("SignUp started ");
            UserEntity userDetails = userRepository.findByUsername(user.getUsername());

            if (userDetails != null) {
                logger.warn("user already exist");
                return ResponseEntity.badRequest().body("user already exist");
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            logger.info("Signup Done Successfully");
            return new ResponseEntity<>("Signup has created Successfully", HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("error in signUp");
            return ResponseEntity.badRequest().body("something wrong");
        }
    }
}


