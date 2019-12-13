package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamToken {
    private String teamName;
    private String expiresIn;
    private UserToken userToken;
}
