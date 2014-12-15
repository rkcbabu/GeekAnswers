/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mailservice;

/**
 *
 
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A singleton class for sending mail messages.
 *  @author spaw1_001
 *   Thanks to zeriet for the mailservice package
 */
public class MailService {

    /**
     * Sends a subject and message to a recipient
     * @param recipient Internet address of the recipient
     * @param subject the subject of the message
     * @param message the message
     * @throws MessagingException
     * 
     * 
     * your email should be configured to allow lesssercureapps 
     * https://www.google.com/settings/security/lesssecureapps
     */
     

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final String USER = "geekanswers1";     // Must be valid user
    private static final String PASSWORD = "answers1geek"; // Must be valid password 
    private static final String FROM = "geekanswers1@gmail.com";     

    private static MailService theService = null;
    private static Session mailSession;
    
    public static void sendMessage(String recipient, String subject, String message) throws MessagingException {

        if ( theService == null ) {
            theService = new MailService();
        }

        MimeMessage mimeMessage = new MimeMessage(mailSession);

	mimeMessage.setFrom(new InternetAddress(FROM));
	mimeMessage.setSender(new InternetAddress(FROM));
	mimeMessage.setSubject(subject);
        mimeMessage.setContent(message, "text/plain");

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        Transport transport = mailSession.getTransport("smtps");
        transport.connect(HOST, PORT, USER, PASSWORD);

        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
        transport.close();

    }

    private MailService() {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", HOST);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.from", FROM);
        props.put("mail.smtps.quitwait", "false");

        mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
    }

   }