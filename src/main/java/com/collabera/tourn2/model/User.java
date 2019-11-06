package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "User")
public class User
{
    @Id
    public String id;
    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String password;
}
