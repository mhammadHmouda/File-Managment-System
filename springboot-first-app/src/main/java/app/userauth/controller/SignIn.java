package app.userauth.controller;

import app.filerepository.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import app.userauth.services.signinservice.intf.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignIn  {
    @Autowired
    private ISignInService signInService;

    @PostMapping("/signIn")
    public ResponseMessage authenticateUser(@RequestBody LoginRequest loginRequest) {
      return signInService.signIn(loginRequest);
    }
}

