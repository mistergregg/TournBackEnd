package com.collabera.tourn2.security;

import com.collabera.tourn2.model.User;
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

    public User generate(User user)
    {
        // Token Expires after 5 days
        long hours = 120;
        long totalMillis = hours * 3600000;

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("id", user.getId());

        User tmp = new User();
        tmp.setToken(Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(totalMillis))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact());

        return tmp;
    }
}
