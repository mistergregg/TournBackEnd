package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken
{
    @Getter
    @Setter
    private String token;

    public JWTAuthenticationToken(String token)
    {
        super(null, null);
        this.token = token;
    }

    @Override
    public Object getCredentials()
    {
        return null;
    }

    @Override
    public Object getPrincipal()
    {
        return null;
    }
}
