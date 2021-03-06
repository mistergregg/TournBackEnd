package com.collabera.tourn2.security;

import com.collabera.tourn2.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTValidator {

    @Value("${token.secret}")
    private String secret;

    public User validateUserToken(String token)
    {
        User user = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            // Creates a new user and gets info from user
            user = new User();

            // Gets the expiration time
            Long exp = (Long)body.get("exp");

            if (System.currentTimeMillis() < exp)
            {
                // Extracts info from token and puts it into user
                user.setUsername(body.getSubject());
                user.setId((String) body.get("id"));
            }
        }catch (Exception e)
        {
            e.printStackTrace();

            user = new User();
        }

        return user;
    }
}
