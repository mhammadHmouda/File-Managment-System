package app.userauth.signin.controller;

import app.response.ResponseMessage;
import app.userauth.model.LoginRequest;
import app.userauth.signin.service.intf.ISignInService;
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
    public ResponseMessage authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
      return signInService.signIn(loginRequest);
    }
}

