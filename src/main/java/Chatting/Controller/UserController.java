package Chatting.Controller;

import Chatting.Dto.LoginResult;
import Chatting.Dto.Result;
import Chatting.Jwt.JwtTokenProvider;
import Chatting.Service.UserService;
import Chatting.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        userService.register(user);
        return "Success";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResult> login(@RequestBody User user) {
        if(userService.login(user))
            return new ResponseEntity<>(LoginResult.builder()
                .jwtToken(jwtTokenProvider.generateToken(user.getId(), user.getRole()))
                .result(Result.SUCCESS)
                .url("/home")
                .build(), HttpStatus.OK);
        return new ResponseEntity<>(LoginResult.builder().result(Result.FAIL).build(),HttpStatus.BAD_REQUEST);
    }
}
