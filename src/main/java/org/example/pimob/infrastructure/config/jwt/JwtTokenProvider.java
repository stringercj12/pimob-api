package org.example.pimob.infrastructure.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String jwtSecret;

  private final long jwtExpirationInMs = 120000L;

  public String generateToken(String email, String role) {
    Date now = new Date();
    Date expiration = new Date(now.getTime() + jwtExpirationInMs);
    Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(expiration).signWith(key).compact();
  }

  public String getEmailFromToken(String token) {
    Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
  }
}
