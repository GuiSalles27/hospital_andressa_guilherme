/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.servico;

import com.mycompany.hospital.dao.SmtpConfigDAO;
import com.mycompany.hospital.dao.entidade.SmtpConfig;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailService {

    public static void enviarRecuperacaoSenha(String para, String link)
            throws MessagingException, UnsupportedEncodingException {

        SmtpConfig cfg = new SmtpConfigDAO().buscarAtiva();
        if (cfg == null) {
            throw new IllegalStateException("Nenhuma configuração SMTP ativa encontrada.");
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(cfg.isAuth()));
        props.put("mail.smtp.host", cfg.getHost());
        props.put("mail.smtp.port", String.valueOf(cfg.getPort()));
        props.put("mail.smtp.ssl.trust", cfg.getHost());

        if (cfg.isStarttls()) props.put("mail.smtp.starttls.enable", "true");
        if (cfg.isSsl())      props.put("mail.smtp.ssl.enable",      "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(cfg.getUsername(), cfg.getPasswordApp());
            }
        });

        // Opcional: ligar logs de SMTP no console
        // session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(cfg.getFromEmail(), cfg.getFromName()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
        message.setSubject("Recuperação de Senha");
        String conteudo = "<p>Você solicitou a redefinição de senha.</p>"
                + "<p><a href=\"" + link + "\">Clique aqui para redefinir</a></p>"
                + "<p>Se não foi você, ignore esta mensagem.</p>";
        message.setContent(conteudo, "text/html; charset=UTF-8");

        Transport.send(message);
    }
}

