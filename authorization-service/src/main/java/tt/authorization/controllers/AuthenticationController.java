package tt.authorization.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public String authenticate() {
        return "success";
    }

}
