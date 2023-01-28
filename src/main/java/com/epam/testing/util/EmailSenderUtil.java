package com.epam.testing.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * EmailSender util. Sends email to user.
 * Uses email.properties for configuration
 *
 * @author rom4ik
 */
public class EmailSenderUtil {
    private static final Logger LOGGER = LogManager.getLogger(EmailSenderUtil.class);
    private static final String emailToSend = "roman.nikitin.pz.2021@lpnu.ua";

    /**
     * Don't let anyone instantiate this class.
     */
    private EmailSenderUtil() {}

    /**
     * @param emailToSend represents email address to which message has to be sent
     * @param text message to send
     */
    public static void sendEmail(String emailToSend, String text){
        LOGGER.info("Sending email starts");
        Properties properties = new Properties();
        try {
            properties.load(EmailSenderUtil.class.getClassLoader().getResourceAsStream("email.properties"));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }

        String senderEmail = properties.getProperty("sender.name");
        String senderPassword = properties.getProperty("sender.password");

        LOGGER.info("Sender email: {}", senderEmail);
        LOGGER.info("Recipient email: {}", emailToSend);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("WebTesting"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailToSend));
            message.setSubject("Test");
            //set the content of the email message
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }

        LOGGER.info("Email message sent successfully");
    }
}
