package app.userauth.signin.service.impl;

import app.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import app.userauth.model.UserEntity;
import app.userauth.repo.DBUserRepository;
import app.userauth.signin.service.intf.ISignInService;
import app.userauth.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService implements ISignInService {
    @Autowired
    DBUserRepository userRepository;

    @Autowired
    AuthUtils authUtils;
    private static final Logger logger = LoggerFactory.getLogger(SignInService.class);

    public ResponseMessage signIn(LoginRequest  authenticationRequest) throws Exception {

        UserEntity user = userRepository.findByUsername(authenticationRequest.getUsername());

        return authUtils.checkValidity(user, authenticationRequest);

    }
}
