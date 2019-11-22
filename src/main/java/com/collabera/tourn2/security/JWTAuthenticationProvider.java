package com.collabera.tourn2.security;

import com.collabera.tourn2.model.JWTAuthenticationToken;
import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserTokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{

    @Autowired
    private JWTValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException
    {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException
    {
        JWTAuthenticationToken JWTToken = (JWTAuthenticationToken)usernamePasswordAuthenticationToken;
        String token = JWTToken.getToken();

        User user = validator.validate(token);

        if(user == null)
        {
            throw new RuntimeException("User Token is invalid");
        }

        return new UserTokenDetails(user.getUsername(), user.getId(), token);
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return JWTAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
