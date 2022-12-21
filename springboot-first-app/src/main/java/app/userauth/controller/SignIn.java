package app.userauth.controller;

import app.userauth.model.intf.ILoginRequest;
import app.userauth.services.signinservice.intf.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> authenticateUser(@RequestBody ILoginRequest loginRequest) {
      return signInService.signIn(loginRequest);
    }
}

