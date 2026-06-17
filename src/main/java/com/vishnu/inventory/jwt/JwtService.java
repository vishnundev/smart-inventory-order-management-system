package com.vishnu.inventory.jwt;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service

public class JwtService {


@Value("${jwt.secret}")
private String secret;

@Value("${jwt.expiration}")
private long expiration;

private SecretKey getSigningKey() {

    return Keys.hmacShaKeyFor(secret.getBytes());

}

public String generateToken(String email) {

    Date now = new Date();

    Date expiry = new Date(now.getTime() + expiration);

    return Jwts.builder()

            .subject(email)

            .issuedAt(now)

            .expiration(expiry)

            .signWith(getSigningKey())

            .compact();

}

public String extractEmail(String token) {

    return extractAllClaims(token).getSubject();

}

public boolean isTokenValid(
        String token,
        String email
) {

    return extractEmail(token)
            .equals(email);

}

private Claims extractAllClaims(
        String token
) {

    return Jwts.parser()

            .verifyWith(getSigningKey())

            .build()

            .parseSignedClaims(token)

            .getPayload();

}


}
