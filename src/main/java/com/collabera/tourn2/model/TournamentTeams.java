package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString

@Document(collection = "TournamentTeams")
public class TournamentTeams {
    @Id
    private String id;
    private String description;
    private List<Team> teams;
    private List<Bracket> brackets;
    private String owner;
    private UserToken userToken;
}
