package com.collabera.tourn2.service;

import com.collabera.tourn2.model.*;
import com.collabera.tourn2.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

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

            teams = teamRepository.findByUserId(user.getId(), PageRequest.of(first, 10));
        }

        return teams;
    }

    public Long getAmountTeams(UserToken userToken)
    {
        User user = tokenService.validate(userToken);
        if(user != null) {
            return teamRepository.countAllByUserId(user.getId());
        }

        return Integer.toUnsignedLong(0);
    }

    public ManagePlayerFromTeam deletePlayerFromTeam(ManagePlayerFromTeam playerFromTeam)
    {
        User user = tokenService.validate(playerFromTeam.getToken());

        Optional<Team> team = teamRepository.findById(playerFromTeam.getTeamId());

        if(team.isPresent())
        {
            if(user.getId().equals(team.get().getOwner()))
            {
                Team rTeam = team.get();
                List<TeamPlayer> players = rTeam.getUserList();
                players.removeIf(e -> e.getUserId().equals(playerFromTeam.getUserId()));
                rTeam.setUserList(players);
                teamRepository.save(rTeam);

                return playerFromTeam;
            }
        }

        return new ManagePlayerFromTeam();
    }

    public Team getTeam(String teamId, String token)
    {
        User user = tokenService.validate(token);

        if(user != null)
        {
            System.out.println(teamId);
            Optional<Team> aTeam = teamRepository.findById(teamId);

            if(aTeam.isPresent())
            {
                System.out.println(aTeam.get());
                return aTeam.get();
            }
        }

        return new Team();
    }

    public List<Team> search(String name, String token)
    {
        List<Team> teams = new ArrayList<Team>();

        if(name.length() >= 2)
        {
            User user = tokenService.validate(token);

            if(user != null)
            {
                teams = teamRepository.findByNameStartsWith(name, PageRequest.of(0, 6, Sort.by(Sort.Direction.ASC, "name")));
            }

            if(teams == null)
            {
                teams = new ArrayList<Team>();
            } else
            {
                for (Team team : teams) {
                    team.setUserList(team.getUserList().stream().filter(aTeam -> aTeam.getUserName().equals(user.getUsername())).collect(Collectors.toList()));
                }
            }
        }

        return teams;
    }

    public List<Team> enterSearch(String name, String token, String page)
    {
        List<Team> teams = new ArrayList<Team>();

        if(name.length() >= 2)
        {
            User user = tokenService.validate(token);

            if(user != null)
            {
                int amount = Integer.parseInt(page);

                if (amount < 1)
                {
                    amount = 1;
                }

                int first = amount - 1;

                teams = teamRepository.findByNameStartsWith(name, PageRequest.of(first, 10));
            }

            if(teams == null)
            {
                teams = new ArrayList<Team>();
            } else
            {
                for (Team team : teams) {
                    team.setUserList(team.getUserList().stream().filter(aTeam -> aTeam.getUserName().equals(user.getUsername())).collect(Collectors.toList()));
                }
            }
        }

        return teams;
    }

    public Long getAmountSearchResults(String name, String userToken)
    {
        User user = tokenService.validate(userToken);

        if(user != null) {
            return teamRepository.countAllByNameStartsWith(name);
        }

        return Integer.toUnsignedLong(0);
    }

    public Long deleteTeam(String id, String token)
    {
        User user = tokenService.validate(token);

        if(user != null)
        {
            Team deleteTeam = getTeam(id, token);

            if(deleteTeam.getOwner().equals(user.getId()))
            {
                teamRepository.deleteById(deleteTeam.getId());

                return Integer.toUnsignedLong(1);
            }
        }

        return Integer.toUnsignedLong(0);
    }
}
