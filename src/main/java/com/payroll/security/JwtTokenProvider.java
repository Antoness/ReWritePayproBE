package com.payroll.security;

import com.payroll.modules.user.User;
import com.payroll.modules.role.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtTokenProvider {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration}")
    private long validityInMs;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        Set<String> authorities = new HashSet<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                authorities.add("ROLE_" + role.getName().toUpperCase());
            }
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("nik", user.getNik());
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());
        claims.put("position", user.getPosition());
        claims.put("privilege", user.getPrivilage());
        claims.put("businessType", user.getBusinessType());
        claims.put("companyId", user.getCompanyId() != null ? user.getCompanyId() : 1L);
        claims.put("authorities", authorities);

        return Jwts.builder()
                .claims(claims)
                .subject(user.getId() != null ? user.getId().toString() : user.getUsername())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).get("username", String.class);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
}