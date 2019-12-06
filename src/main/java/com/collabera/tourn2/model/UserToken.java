package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String username;
    private String expiresIn;
    private String token;
}
