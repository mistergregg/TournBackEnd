package com.collabera.tourn2.service;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.security.JWTTokenGenerator;
import com.collabera.tourn2.security.JWTValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService
{
    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    private JWTValidator jwtValidator;

    public UserToken generate(User user)
    {
        if(user != null)
        {
            return jwtTokenGenerator.generateUserToken(user);
        }
        return null;
    }

    public User validate(UserToken userToken)
    {
        return jwtValidator.validateUserToken(userToken.getToken());
    }
}
