package com.codecool.grannymanager.security;

import com.codecool.grannymanager.constans.AppConstants;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {


    public String generateToken(
            @NotNull String userName,
            SimpleGrantedAuthority authority
            ) {
        return Jwts
                .builder()
                .claim("authorities", authority)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))  // The token will expire in 1 day
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public ArrayList<SimpleGrantedAuthority> extractAuthorities(String jwt) {
        Claims claims = extractAllClaims(jwt);
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (claims.containsKey("authority")) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority((String) claims.get("authority"));
            authorities.add(authority);
        }
        // Add more authorities if needed
        return authorities;
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
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

    public String getUserNameFromHeader(String header) {
        String token = header.substring(AppConstants.TOKEN_BEGIN_INDEX);
        return extractUserName(token);
    }

    private Key getSigningKey() {
//        Dotenv dotenv = Dotenv.configure().load();
        String secretKey = AppConstants.SECRET_KEY;
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
