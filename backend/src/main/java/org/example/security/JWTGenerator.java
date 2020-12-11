package org.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTGenerator {

    private static final String secret = "12345678123456781234567812345678123456781234567812345678123456781234567812345678";
    private static final String ID = "id";

    public String generate(Long id) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claim(ID, id)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public Long parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(ID, Long.class);
    }
}
