package app.user.auth.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.user.repo.DBUserRepository;
import app.user.model.LoginRequest;
import app.user.model.UserEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    DBUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("SignIn started");
            UserEntity userDetails = userRepository.findByUsername(loginRequest.getUsername());

            if (userDetails == null) {
                logger.warn("invalid Username user not found");
                return new ResponseEntity<>("invalid Username", HttpStatus.NOT_FOUND);
            }
            if (!passwordEncoder().matches(loginRequest.getPassword(), userDetails.getPassword())) {
                logger.warn("invalid Password not match with username");
                return new ResponseEntity<>("invalid Password", HttpStatus.NOT_FOUND);
            }
            logger.info(" signIn Done successfully");
            return new ResponseEntity<>("SignIn has done Successfully", HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("Failed signIn");
            return ResponseEntity.badRequest().body("Failed signIn");
        }
    }
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> addNewUser(@RequestBody UserEntity user) {

        try {
            logger.info("SignUp started");
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