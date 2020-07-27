package Chatting.Controller;

import Chatting.Dto.LoginResult;
import Chatting.Dto.Result;
import Chatting.Jwt.JwtTokenProvider;
import Chatting.Service.UserService;
import Chatting.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public LoginResult login(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return LoginResult.builder()
                .jwtToken(jwtTokenProvider.generateToken(user.getId()))
                .result(Result.SUCCESS)
                .url("/home")
                .build();
    }
}
