package com.mycompany.hospital.dao;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class PasswordResetTokenDAO {

    public static String generateToken() throws Exception {
        byte[] bytes = new byte[32];
        SecureRandom.getInstanceStrong().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String sha256Hex(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public void saveToken(int userId, String tokenHash, int expireMinutes) {
        String sql = "INSERT INTO password_reset_token (user_id, token_hash, created_at, expires_at, used) VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL ? MINUTE), 0)";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, tokenHash);
            ps.setInt(3, expireMinutes);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer validateToken(String tokenHash) {
        String sql = "SELECT user_id FROM password_reset_token WHERE token_hash = ? AND used = 0 AND expires_at > NOW()";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tokenHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void markTokenUsed(String tokenHash) {
        String sql = "UPDATE password_reset_token SET used = 1 WHERE token_hash = ?";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tokenHash);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
