package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.security.JWTTokenGenerator;
import com.collabera.tourn2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class TokenController
{
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @PostMapping("/generate")
    public UserToken generate(@RequestBody User user)
    {

        if(userService.checkLogin(user).getUsername() != null)
        {
            return jwtTokenGenerator.generateUserToken(user);
        }

        return new UserToken();
    }

    @GetMapping("/user")
    public User user()
    {
        return new User();
    }
}
