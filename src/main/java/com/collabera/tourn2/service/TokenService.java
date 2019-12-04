package com.collabera.tourn2.service;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.security.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService
{
    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    public UserToken generate(User user)
    {
        if(user != null)
        {
            return jwtTokenGenerator.generate(user);
        }
        return null;
    }
}
