package vn.id.vuductrieu.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.id.vuductrieu.backend.entity.User;

import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration}")
    private int JWT_EXPIRATION;
    public String generateToken(User user) {
        Date date = new Date();
        Date expiryDate = new Date(date.getTime() + JWT_EXPIRATION);
        Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryDate)
                .withIssuedAt(date)
                .withClaim("id", user.getId())
                .sign(algorithm);
    }

    public String getUsernameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(JWT_SECRET.getBytes())).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid token", e);
        }
        return false;
    }
}
