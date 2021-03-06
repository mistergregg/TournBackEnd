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

@Document(collection = "Team")
public class Team {
    @Id
    private String id;
    private String name;
    private String description;
    private String owner;
    private String ownerName;
    private List<TeamPlayer> userList;
    private Integer wins;
    private Integer looses;
    private Integer teamSize;
    private UserToken userToken;
}
