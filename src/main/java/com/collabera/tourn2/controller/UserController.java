package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.service.TokenService;
import com.collabera.tourn2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public UserToken checkLogin(@RequestBody User user)
    {
        User loggedInUser = userService.checkLogin(user);

        return loggedInUser != null ? tokenService.generate(loggedInUser) : new UserToken();
    }

    @PostMapping("/createUser")
    public UserToken createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody UserToken user)
    {
        return userService.getUser(user);
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }
}
