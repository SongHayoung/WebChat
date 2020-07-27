package Chatting.Jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final static String SECRET_KEY = "aefwkj@$#";
    private final static long tokenValidMilsecond = 1000L * 60 * 60;

    public String generateToken(String name) {
        Date now = new Date();
        return Jwts.builder()
                .setId(name)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilsecond))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getUserNameFromJwt(String jwt) {
        return getClaims(jwt).getBody().getId();
    }

    public boolean validateToken(String jwt) {
        return this.getClaims(jwt) != null;
    }

    private Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
        } catch (SignatureException e) {
            throw e;
        } catch (MalformedJwtException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (UnsupportedJwtException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
