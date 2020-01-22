package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.ManagePlayerFromTeam;
import com.collabera.tourn2.model.Team;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController
{
    @Autowired
    TeamService teamService;

    @PostMapping("/add")
    public Team AddTeam(@RequestBody Team team)
    {
        return teamService.createTeam(team);
    }

    @PostMapping("/deletePlayer")
    public ManagePlayerFromTeam DeletePlayerFromTeam(@RequestBody ManagePlayerFromTeam player)
    {
        return teamService.deletePlayerFromTeam(player);
    }

    @PostMapping("/getAll")
    public List<Team> getTeams(@RequestBody UserToken token)
    {
        return teamService.getTeams(token);
    }

    @GetMapping("/get/{id}")
    public Team getTeam(@PathVariable("id") String id, @RequestHeader("token") String token)
    {
        return teamService.getTeam(id, token);
    }

    @PostMapping("/getAmount")
    public Long getAmountTeams(@RequestBody UserToken token)
    {
        return teamService.getAmountTeams(token);
    }

    @GetMapping("/search/{name}")
    public List<Team> search(@PathVariable("name") String name, @RequestHeader("token") String token)
    {
        return teamService.search(name, token);
    }

    @GetMapping("/enterSearch/{name}/{page}")
    public List<Team> searchEnter(@PathVariable("name") String name, @PathVariable("page") String page, @RequestHeader("token") String token)
    {
        return teamService.enterSearch(name, token, page);
    }

    @GetMapping("/sAmount/{name}")
    public Long getSearchAmount(@PathVariable("name") String name, @RequestHeader("token") String token)
    {
        return teamService.getAmountSearchResults(name, token);
    }

    @DeleteMapping("/deleteTeam/{id}")
    public Long deleteTeam(@PathVariable("id") String id, @RequestHeader("token") String token)
    {
        return teamService.deleteTeam(id, token);
    }
}
