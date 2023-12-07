package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}") // Lấy giá trị khóa bí mật từ cấu hình ứng dụng
    private String secretKey;

    @Value("${jwt.expiration}") // Lấy thời gian hết hạn từ cấu hình ứng dụng
    private long validityInMilliseconds;

    public String generateToken(String username) {
        // Tạo JWT token từ username và khóa bí mật
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Kiểm tra tính hợp lệ của JWT token
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e ) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        // Lấy username từ JWT token
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}