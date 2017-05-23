package payment.aspects.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import commons.util.Endpoints;
import commons.util.EstampalaTools;
import payment.pojos.PaymentCreator;

@Aspect
public class PaymentNotification {
	
	@Pointcut("execution(* payment.controllers.PaymentController.create*(..))")
	public void action() {
	}
	
	@After("action()")
	public void afterAction(JoinPoint joinPoint) throws IOException {
		
		PaymentCreator element = (PaymentCreator)joinPoint.getArgs()[0];
		List<String> pathParameters = new ArrayList<String>();
		pathParameters.add(element.getOwner().toString());
		
		String email = EstampalaTools.invokeGetRestServices(Endpoints.USERS_EMAIL, pathParameters, null, String.class);
		
		Properties props = new Properties();
		InputStream input = PaymentNotification.class.getClass().getResourceAsStream("/mail.properties");
        props.load( input );
	 
		Session session = Session.getDefaultInstance(props,
		                         new javax.mail.Authenticator() {
		                         protected PasswordAuthentication getPasswordAuthentication() {
		                             return new PasswordAuthentication(props.getProperty("mail.smtp.user"),
		                                               props.getProperty("mail.smtp.password"));
		                         }
		                         });
		 
		try {
		    
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress("admin@estampala.com"));
		    message.setRecipients(Message.RecipientType.TO,
		              InternetAddress.parse(email));
		    message.setSubject("Notificacion de venta");
		    message.setText("Estampala notifica compra de producto por un valor de: " + element.getTotal());
		    
		    Transport.send(message);
		    
		} catch (MessagingException e) {
		    System.out.println("unable to send email: " + e);
		}
	}
}
