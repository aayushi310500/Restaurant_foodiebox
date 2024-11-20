package com.restaurant.restaurant.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JWTHelper {
    private String SECRET_KEY="f908534c0267817df368eaafe38dbf3676ad6d7fcb992251b48ac3b8ca3792d8";

    public String extractUsername(String token) {
        return exctractClaim(token, Claims::getSubject);
    }
    public Date exctractExpiration(String token) {
        return exctractClaim(token, Claims::getExpiration);
    }

    public <T> T exctractClaim(String token, Function<Claims ,T> claimsResolver){
        final Claims claims = exctractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims exctractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return exctractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 60)) // Token valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) {
//            final String extractedUsername = extractUsername(token);
        //return (extractedUsername.equals(username) && !isTokenExpired(token));
        return !isTokenExpired(token);
    }
}
