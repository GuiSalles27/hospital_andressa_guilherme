/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.SmtpConfig;
import java.sql.*;

public class SmtpConfigDAO {

    public SmtpConfig buscarAtiva() {
        String sql = "SELECT * FROM smtp_config WHERE active = 1 LIMIT 1";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SmtpConfig c = new SmtpConfig();
                c.setId(rs.getInt("id"));
                c.setProvider(rs.getString("provider"));
                c.setFromEmail(rs.getString("from_email"));
                c.setFromName(rs.getString("from_name"));
                c.setHost(rs.getString("host"));
                c.setPort(rs.getInt("port"));
                c.setUsername(rs.getString("username"));
                c.setPasswordApp(rs.getString("password_app"));
                c.setAuth(rs.getBoolean("auth"));
                c.setStarttls(rs.getBoolean("starttls"));
                c.setSsl(rs.getBoolean("ssl"));
                c.setActive(rs.getBoolean("active"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ativar(int id) throws SQLException {
        String off = "UPDATE smtp_config SET active = 0";
        String on  = "UPDATE smtp_config SET active = 1 WHERE id = ?";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement psOff = con.prepareStatement(off);
             PreparedStatement psOn  = con.prepareStatement(on)) {
            psOff.executeUpdate();
            psOn.setInt(1, id);
            psOn.executeUpdate();
        }
    }

    // salvar/atualizar se quiser manter via tela
}

