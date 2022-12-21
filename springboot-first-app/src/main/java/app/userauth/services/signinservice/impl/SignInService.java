package app.userauth.services.signinservice.impl;
import app.userauth.model.UserEntity;
import app.userauth.model.intf.ILoginRequest;
import app.userauth.repo.DBUserRepository;
import app.userauth.services.signinservice.intf.ISignInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static app.userauth.encription.PasswordEncode.passwordEncoder;

@Service
public class SignInService implements ISignInService {
    @Autowired
    DBUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignInService.class);

    public ResponseEntity<?>  signIn (ILoginRequest loginRequest){
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


}
