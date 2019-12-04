package com.collabera.tourn2.security;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenGenerator
{
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expires}")
    private long totalMillis;

    public UserToken generate(User user)
    {
        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("id", user.getId());
        claims.put("iat", new Date(System.currentTimeMillis()));

        Date exp = new Date(System.currentTimeMillis() + totalMillis);
        claims.put("exp", exp);

        UserToken tmp = new UserToken();
        tmp.setToken(Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact());

        tmp.setExp(String.valueOf(exp.getTime()));
        tmp.setUsername(user.getUsername());

        return tmp;
    }
}
