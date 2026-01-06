//package com.example.demo.util;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import com.example.demo.entity.User;
////
////@Component
////public class JwtUtil {
////
////    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
////    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours
////
////    public String generateToken(String email, Role role) {
////        return Jwts.builder()
////                .setSubject(email)
////                .claim("role", role.name())
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
////                .signWith(key)
////                .compact();
////    }
////
////    public String extractEmail(String token) {
////        return Jwts.parserBuilder()
////                .setSigningKey(key)
////                .build()
////                .parseClaimsJws(token)
////                .getBody()
////                .getSubject();
////    }
////
////    public boolean validateToken(String token) {
////        try {
////            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
////            return true;
////        } catch (Exception e) {
////            return false;
////        }
////    }
////}
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    // Use a strong secret key (in production, store in application.properties)
//    private final SecretKey key = Keys.hmacShaKeyFor(
//            Decoders.BASE64.decode("your_very_long_secure_secret_key_here_at_least_256_bits_longgggggggggggggggggggg")
//    );
//
//    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours
//
//    public String generateToken(String email, String role) {
//        return Jwts.builder()
//                .subject(email)
//                .claim("role", role)
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(key)
//                .compact();
//    }
//
//    public String extractEmail(String token) {
//        return parseClaims(token).getSubject();
//    }
//
//    public String extractRole(String token) {
//        return parseClaims(token).get("role", String.class);
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            parseClaims(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//}

package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}