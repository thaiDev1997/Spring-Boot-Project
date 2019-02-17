package com.example.demo.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days

    static final String SECRET = "ThisIsASecret";

    static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)) // now time + 10 days
                .signWith(SignatureAlgorithm.HS512, SECRET).compact(); // pass SECRET String to Sign in to JWT
        // JWT : Json Web Token
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT); // add Token to HEADER with Authorization-Token Access
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING); // get value of Key is Authorization
        if (token != null) {
            try {
                // parse the token, use SECRET to sign in to JWT
                // get username bases on Token
                String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                        .getSubject();
                System.out.println("USERNAME : " + username);
                return username != null ? new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()) : null;
            } catch (Exception ex) {
                return null;
            }
        }
        // if this token is null or token is invalid (isn't exist in memory saving token
        return null;
    }

}
