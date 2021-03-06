package py.com.poraplz.cursomc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.nanoTime()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public boolean isValidToken(String token){
        Claims claims = getClaims(token);
        if(claims != null){
            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            if(userName != null && isValidExpirationTime(expirationDate))
                return true;
        }
        return false;

    }

    public boolean isValidExpirationTime(Date expirationDate){
        Date now = new Date(System.currentTimeMillis());
        return expirationDate != null && now.before(expirationDate);
    }

    public boolean isValidExpirationTime(String token){
        Date expirationDate = null;
        Date now = new Date(System.currentTimeMillis());
        Claims claims = getClaims(token);
        if(claims != null){
            expirationDate = claims.getExpiration();
        }
        return expirationDate != null && now.before(expirationDate);

    }

    public String getUserName(String token){
        Claims claims = getClaims(token);
        if(claims != null){
            return claims.getSubject();
        }
        return null;

    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody();

        }catch (Exception e){
            return null;
        }
    }

}
