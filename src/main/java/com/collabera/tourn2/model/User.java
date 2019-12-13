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

@Document(collection = "User")
public class User
{
    class UserTeams {
        String teamId;
        String teamName;
    }

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private List<UserTeams> teams;
    private int score;
}
