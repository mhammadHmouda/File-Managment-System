package app.userauth.controller;

import app.filerepository.response.ResponseMessage;
import app.userauth.model.UserEntity;
import app.userauth.services.signupservice.intf.ISignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class SignUp {
    @Autowired
    private ISignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseMessage signUp(@RequestBody UserEntity user) {
        return  signUpService.signUp(user);
    }
}
