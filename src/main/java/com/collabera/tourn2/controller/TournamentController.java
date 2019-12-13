package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.TournamentTeams;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.service.TokenService;
import com.collabera.tourn2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournaments")
public class TournamentController
{
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/addTeam")
    public UserToken AddTeam(@RequestBody TournamentTeams tourn)
    {
        return null;
    }
}
