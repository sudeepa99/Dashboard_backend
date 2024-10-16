package com.dashboard.example.Dashboard.utill;

import com.dashboard.example.Dashboard.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTToken {

    private final long accessTokenExpiryTime= 80000;
    private final long refreshTokenExpiryTime= 1000000;
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public List<?> generateAccessToken(User user){
        try{
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getUser_id());
            Date issueAt = new Date(System.currentTimeMillis());
            Date expireAt = new Date(System.currentTimeMillis() +  1000*60*60);
            String token =  Jwts.builder()
                    .claims(claims)
                    .subject(user.getEmail())
                    .issuedAt(issueAt)
                    .expiration(expireAt)
                    .signWith(getSignKey())
                    .compact();
            return List.of(token,expireAt);
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
    }
    public String createAccessToken(User user ) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiryTime))
                .signWith(getSignKey())
                .compact();
    }

    public List<?> generateRefreshToken(User user){
        try{
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getUser_id());
            Date issueAt = new Date(System.currentTimeMillis());
            Date expireAt = new Date(System.currentTimeMillis() +  1000*60*60*24);
            String token = Jwts.builder()
                    .claims(claims)
                    .subject(user.getEmail())
                    .issuedAt(issueAt)
                    .expiration(expireAt)
                    .signWith(getSignKey())
                    .compact();
            return List.of(token,expireAt);
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
    }

    public String createRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiryTime))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        String secretKey = "0+laBH9a0XyOAsP+H6JVdjoMliiG2EdKmby5QL2JLh8=";
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("SECRET_KEY is not set");
        }
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
