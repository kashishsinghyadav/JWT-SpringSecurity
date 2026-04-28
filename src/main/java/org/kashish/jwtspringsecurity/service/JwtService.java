package org.kashish.jwtspringsecurity.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private String secretKey = "";

    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256);

            SecretKeySpec secretKeySpec =
                    new SecretKeySpec(
                            keyGenerator.generateKey().getEncoded(),
                            "HmacSHA256"
                    );

            secretKey = Base64.getEncoder()
                    .encodeToString(secretKeySpec.getEncoded());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String genrateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + 1000 * 60 * 60 * 30))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUserNamr(String token) {
        return "";
    }

    public boolean validateToekn(String token, UserDetails userDetails) {
        return false;
    }
}


