package com.epam.javalab13.util;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Util class for sending emails by smtp protocol
 */
public class MailSender {

    private static Logger logger = Logger.getLogger(MailSender.class);

    private String username;
    private String password;
    private Properties props;

    /*
    Initialization mail server
     */
    public MailSender(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    /**
     * Send mails for list of users
     * @param subject the subject of mail
     * @param text the main content of mail
     * @param emails list of users emails for sending
     */
    public void sendEmailsBatch(String subject, String text, ArrayList<String> emails){

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));


            for(String email:emails) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
            }

            message.setSubject(subject);
            message.setContent(
                    text + "</br><p><a href=\"http://localhost:8080/PayKick\">PayKick</a></p>",
                    "text/html;charset=utf-8");

            Transport.send(message);
            logger.info("Emails sent successfully!");
        } catch (Exception e) {
            logger.error("Cant send emails:",e);
        }
    }

    /**
     * Send email for one user
     * @param subject the subject of mail
     * @param text the main content of mail
     * @param email the user email address
     */
    public void sendEmail(String subject, String text, String email){

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));


            message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));

            message.setSubject(subject);
            message.setContent(
                    text + "<p><a href=\"http://localhost:8080/PayKick\">PayKick</a></p>",
                    "text/html;charset=utf-8");

            Transport.send(message);
            logger.info("Emails sent successfully!");
        } catch (Exception e) {
            logger.error("Cant send emails:",e);
        }
    }

}
