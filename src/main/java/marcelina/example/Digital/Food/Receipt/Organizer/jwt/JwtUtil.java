package marcelina.example.Digital.Food.Receipt.Organizer.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "duhetjjjjjjjjjjjjjjjjjjjjjjjjjjgfdsqserdtfygmlkjggfxcvbnjnjugbnTaZgjidhesh";
    private final long EXPIRATION_TIME = 86400000;

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return parseClaims(token).getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public boolean validateToken(String token){
        try {
            parseClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
