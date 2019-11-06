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

@Document(collection = "Tournament")
public class Tournament {
    @Id
    public String id;
    public String description;
    public List<Team> teams;
    public List<Bracket> brackets;
}
