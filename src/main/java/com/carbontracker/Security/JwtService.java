package com.carbontracker.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "mySecretKeyMySecretKeyMySecretKey12345";


    // Generate Token
    public String generateToken(String email) {


        return Jwts.builder()

                .subject(email)

                .issuedAt(new Date(System.currentTimeMillis()))

                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                )

                .signWith(getKey())

                .compact();
    }


    // Secret Key Generate
    private SecretKey getKey(){

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );

    }


    // Extract Email from Token
    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token, String email) {

        String tokenEmail = extractEmail(token);

        return tokenEmail.equals(email);
    }
}
