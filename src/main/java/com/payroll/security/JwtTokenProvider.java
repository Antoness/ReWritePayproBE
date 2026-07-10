package com.payroll.security;

import com.payroll.modules.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret:base64EncodedSecretKeyForPayrollApp2026ModernStack}")
    private String secretKey;

    @Value("${app.jwt.expiration:86400000}")
    private long validityInMilliseconds;

    private SecretKey key;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(User user) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("position", user.getPosition());
        claimsMap.put("privilage", user.getPrivilage());
        claimsMap.put("nik", user.getNik());
        claimsMap.put("fullName", user.getFullName());
        claimsMap.put("division", user.getDivision());
        claimsMap.put("leader", user.getLeader());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .subject(user.getUsername())
                .claims(claimsMap)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
