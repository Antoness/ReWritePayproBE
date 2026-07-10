package com.payroll.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        // Sistem baru selalu menyimpan sebagai BCrypt
        return bcrypt.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            return false;
        }

        // 1. Cek jika password di DB adalah format BCrypt (biasanya diawali $2a$ atau $2b$)
        if (encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$")) {
            return bcrypt.matches(rawPassword, encodedPassword);
        }

        // 2. Jika bukan BCrypt, maka kita anggap sebagai MD5 (Legacy dari sistem lama atau hasil reset massal)
        String rawMd5 = md5Hex(rawPassword.toString());
        return rawMd5.equalsIgnoreCase(encodedPassword);
    }

    // Helper untuk generate MD5 Hex (sama dengan fungsi MD5() di MySQL)
    private String md5Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
