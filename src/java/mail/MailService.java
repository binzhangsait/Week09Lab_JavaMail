/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author 798419
 */
public class MailService {

    public static void main(String[] args) throws MessagingException {
        MailService ms = new MailService();
        String fromEmail = "james.smith.dummy1@gmail.com";
        String password = "pass123word";
        String toEmail = "binzhangab@gmail.com"; //This is a FAKE email address. Generated for temp usage!
        String subject = "This is a test email";
        String htmlContent = "<h1>Hello world</h1>";
        ms.sendHTMLEmail(fromEmail, password, toEmail, subject, htmlContent);
    }

    public void sendHTMLEmail(String fromEmail, String password, String toEmail, String subject, String htmlContent) throws MessagingException {
        Properties props = new Properties();
        String host = "smtp.gmail.com";
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", host);
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setSubject(subject);
        message.setContent(htmlContent, "text/html");

        Address fromAddress = new InternetAddress(fromEmail);
        Address toAddress = new InternetAddress(toEmail);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        try (Transport transport = session.getTransport()) {
            transport.connect(fromEmail, password);
            transport.sendMessage(message, message.getAllRecipients());
        }
        System.out.println("DONE");

    }

}
