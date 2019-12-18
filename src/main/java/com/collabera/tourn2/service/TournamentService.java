package com.collabera.tourn2.service;

import com.collabera.tourn2.model.*;
import com.collabera.tourn2.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Autowired
    TeamRepository teamRepository;

    public User createTournament(TournamentTeams tournamentTeams)
    {
        User user = tokenService.validate(tournamentTeams.getUserToken());

        if(user.getUsername() == null)
        {
            return new User();
        }

        if(tournamentTeams.getOwner().equals(""))
        {
            return new User();
        }

        if(tournamentTeams.getId().equals(""))
        {
            return new User();
        }

        if(tournamentTeams.getDescription().equals(""))
        {
            return new User();
        }

        tournamentTeams.setOwner(user.getUsername());

        return null;
    }

    public Team createTeam(Team team)
    {
        User user = tokenService.validate(team.getUserToken());

        if(user.getUsername() == null)
        {
            return new Team();
        }

        if(team.getName().length() < 3)
        {
            return new Team();
        }

        if(team.getDescription().length() < 3)
        {
            return new Team();
        }

        User userData =  userService.loadUserByUsername(user.getUsername());

        if(userData == null)
        {
            return new Team();
        }

        Team saveTeam = new Team();

        saveTeam.setOwner(userData.getId());
        saveTeam.setOwnerName(userData.getUsername());

        saveTeam.setDescription(team.getDescription());
        saveTeam.setName(team.getName());

        saveTeam.setWins(0);
        saveTeam.setLooses(0);

        saveTeam.setTeamSize(1);

        List<TeamPlayer> teamList = new ArrayList<TeamPlayer>();
        teamList.add(new TeamPlayer(userData.getId(), userData.getUsername()));

        saveTeam.setUserList(teamList);

        teamRepository.save(saveTeam);

        return saveTeam;
    }

    public List<Team> getTeams(UserToken userToken)
    {
        User user = tokenService.validate(userToken);

        List<Team> teams = new ArrayList<Team>();


        if(user != null)
        {
            int amount = Integer.parseInt(userToken.getExpiresIn());

            if (amount < 1)
            {
                amount = 1;
            }

            int first = amount - 1;

            teams = teamRepository.findAllByOwner(user.getId(), PageRequest.of(first, 10));
        }

        return teams;
    }

    public Long getAmountTeams(UserToken userToken)
    {
        User user = tokenService.validate(userToken);
        if(user != null) {
            return teamRepository.countAllByOwner(user.getId());
        }

        return Integer.toUnsignedLong(0);
    }

    private Long getAmountTeams(String id)
    {
        return teamRepository.countAllByOwner(id);
    }
}
