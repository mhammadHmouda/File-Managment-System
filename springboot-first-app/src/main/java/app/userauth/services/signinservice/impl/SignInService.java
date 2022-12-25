package app.userauth.services.signinservice.impl;
import app.config.TokenProvider;
import app.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import app.userauth.repo.DBUserRepository;
import app.userauth.services.signinservice.intf.ISignInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SignInService implements ISignInService {
    @Autowired
    DBUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignInService.class);



    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    public ResponseMessage signIn(LoginRequest  authenticationRequest) throws Exception {



        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(userDetails);

        final String token = TokenProvider.generateToken(userDetails);

        String msg = "SignIn has Done Successfully \" "  +  " \" token is :    "  + token;
        return ResponseMessage.getInstance(msg);
    }
}
