package com.codecool.grannymanager.security;

import com.codecool.grannymanager.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


    public String generateToken(User user) {
        return generateToken(user.getUserName(), new SimpleGrantedAuthority(user.getRole().name()));
    }


    public String generateToken(
            @NotNull String userName,
            SimpleGrantedAuthority authority
            ) {
        HashMap<String, SimpleGrantedAuthority> authorities = new HashMap<>();
        authorities.put("authority", authority);
        return Jwts
                .builder()
                .setClaims(authorities)
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

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(System.getenv("SECRET_KEY"));
        return Keys.hmacShaKeyFor(keyByte);
    }
}
