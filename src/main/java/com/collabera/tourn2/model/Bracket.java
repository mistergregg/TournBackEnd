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

@Document(collection = "Bracket")
public class Bracket
{
    @Id
    String id;
    List<User> players;
    Integer round;
}
