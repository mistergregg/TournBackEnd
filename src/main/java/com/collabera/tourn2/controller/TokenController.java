package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.User;
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
    public User generate(@RequestBody User user)
    {

        if(userService.checkLogin(user).getUsername() != null)
        {
            return jwtTokenGenerator.generate(user);
        }

        return new User();
    }
}
