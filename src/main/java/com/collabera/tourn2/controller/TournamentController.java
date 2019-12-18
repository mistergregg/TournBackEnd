package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.Team;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController
{
    @Autowired
    TournamentService tournamentService;

    @PostMapping("/addTeam")
    public Team AddTeam(@RequestBody Team team)
    {
        return tournamentService.createTeam(team);
    }

    @PostMapping("/getTeams")
    public List<Team> getTeams(@RequestBody UserToken token)
    {
        return tournamentService.getTeams(token);
    }

    @PostMapping("/getAmountTeams")
    public Long getAmountTeams(@RequestBody UserToken token)
    {
        return tournamentService.getAmountTeams(token);
    }
}
