package com.collabera.tourn2.security;

import com.collabera.tourn2.model.JWTAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This Class Filters out URLS
 */
public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter
{
    public JWTAuthenticationTokenFilter(String defaultFilterProcessesUrl) {
        super("/authenticate**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {

        String header = httpServletRequest.getHeader("Authorization");

        if(header == null || !header.startsWith("Token "))
        {
            throw new RuntimeException("Token is Missing!");
        }

        String authenticationToken = header.substring(6);

        JWTAuthenticationToken token = new JWTAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
