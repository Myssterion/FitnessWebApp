package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import bean.MessageBean;
import repository.MessageRepository;

public class MessageService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MessageBean> messages = new ArrayList<>();

	public MessageService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<MessageBean> getMessages() {
		return messages;
	}
	
	public void loadMessages() {
		messages = (ArrayList<MessageBean>) MessageRepository.getUnreadMessagesForAdvisors();
	}
	
	public boolean updateStatus(int messageId) {
		return MessageRepository.updateStatus(messageId);
	}
	
	public void sendMessage(int userId, String message, InputStream fileContent, String fileName, String contentType) {
		String email = "aldin.tursic@gmail.com";//UserRepository.getRegularUserEmail(userId);
		sendEmail(email, message, fileContent, fileName,contentType);
		
	}
	
	public static void sendEmail(String recipient, String content, InputStream fileContent, String fileName, String contentType) {
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", "smtp.gmail.com"); // Use the correct SMTP host
	    properties.put("mail.smtp.port", "587"); // Port for STARTTLS
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

	    try (FileInputStream input = new FileInputStream("C:/Users/Korisnik/Desktop/eclipse-java/AdvisorApp/resource/mail.properties")) {
	        properties.load(input);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    String username = properties.getProperty("mail.username");
	    String password = properties.getProperty("mail.password");
	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        // Create a message
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	        message.setSubject("Customer Support"); // Set the subject

	        // Create a multipart message for email
	        MimeMultipart multipart = new MimeMultipart();

	        // Create the message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(content);
	        multipart.addBodyPart(messageBodyPart);

	        // Attach file if provided
	        if (fileContent != null && fileName != null && contentType != null) {
	            messageBodyPart = new MimeBodyPart();
	            ByteArrayDataSource source = new ByteArrayDataSource(fileContent, contentType);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(fileName);
	            multipart.addBodyPart(messageBodyPart);
	        }

	        // Set the complete message parts
	        message.setContent(multipart);

	        // Send the email
	        Transport.send(message);

	        System.out.println("Email sent successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
 }
