package app.userauth.utils;

import app.response.ResponseMessage;
import app.userauth.config.TokenProvider;
import app.userauth.model.LoginRequest;
import app.userauth.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

import static app.userauth.encription.PasswordEncode.passwordEncoder;
import static app.userauth.signin.constant.ConstantAuth.INVALID_AUTH;


@Service
public class AuthUtils {
    private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    public ResponseMessage checkValidity(UserEntity userDetails, LoginRequest loginRequest) throws AuthException {
        try {

            if (userDetails == null ||
                    !passwordEncoder().matches(loginRequest.getPassword(), userDetails.getPassword())) {
                logger.warn("Invalid name or password!");
                return ResponseMessage.getInstance("Invalid name or password!");
            }
            final UserDetails user = jwtInMemoryUserDetailsService
                    .loadUserByUsername(loginRequest.getUsername());

            final String token = TokenProvider.generateToken(user);

            String msg = "SignIn has Done Successfully \" " + " \" token is :    " + token;
            return ResponseMessage.getInstance(msg);
        }catch (Exception e){
            throw new AuthException(INVALID_AUTH);
        }
    }

}
