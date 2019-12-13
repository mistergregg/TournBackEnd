package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamPlayer {

    public TeamPlayer(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    private String userId;
    private String userName;
}
