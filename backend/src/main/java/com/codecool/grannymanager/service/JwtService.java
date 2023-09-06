package com.codecool.grannymanager.service;

import com.codecool.grannymanager.config.constans.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {


    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().
                parseClaimsJws(token).
                getBody();
    }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(AppConstants.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
