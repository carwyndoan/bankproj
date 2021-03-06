package edu.miu.finalProject.javaMailApi;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmailClass {
    public static void sendMailTo(String receipt) throws Exception{
        System.out.println("preparing to send.....");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String myAccountEmail = "bereketasmel@gmail.com";
        final String password = "***********";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, receipt);
        Transport.send(message);
        System.out.println("Message Sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String reciept){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reciept));
            message.setSubject("Account Changes.");
            message.setText("Your account has been modified. Please login into the system and check recent activities.");
            return message;
        } catch (Exception e) {
            Logger.getLogger(SendEmailClass.class.getName()).log(Level.SEVERE, null, e);
        }

         return null;
    }
}
