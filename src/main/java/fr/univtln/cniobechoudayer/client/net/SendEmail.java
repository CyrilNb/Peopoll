package fr.univtln.cniobechoudayer.client.net;

/**
 * Créé par Corentin le 25/10/2017
 */

import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

    public static void sendEmail(Poll poll){

            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            // Get a Properties object
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");
            final String username = "projectpeopoll@gmail.com";//
            final String password = "peopollD13";
            try{
                Session session = Session.getDefaultInstance(props,
                        new Authenticator(){
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }});

                // -- Create a new message --
                Message msg = new MimeMessage(session);

                // -- Set the FROM and TO fields --
                msg.setFrom(new InternetAddress("Peopoll"));
                msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(poll.getMailCreator(),false));
                if(poll.getIdFinalChoice() != 0) {
                    Choice choice = Choice.findById(poll.getIdPoll());
                    msg.setSubject("The final date has been chosen for : " + poll.getTitle());
                    msg.setText("Set a reminder for the " + choice.getDateChoice() + " " + Choice.getFormattedDate(choice.getStartingTime(), true) + " " + Choice.getFormattedDate(choice.getEndingTime(), false) + "\nLocation : " + poll.getLocation() + "\nDescription : " +poll.getDescription());
                }else{
                    msg.setSubject("Your codes to manage your new poll : " + poll.getTitle());
                    msg.setText("Hi !\nHere are your codes, take care to keep them safe !\nAccess code : " + poll.getIdPoll() + "\nManagement code : " + poll.getManagerCode());
                }
                msg.setSentDate(new Date());
                Transport.send(msg);
                System.out.println("Message sent.");
            }catch (MessagingException e){ System.out.println("Erreur d'envoi, cause: " + e);} catch (PersistanceException e) {
                e.printStackTrace();
            }
    }

}