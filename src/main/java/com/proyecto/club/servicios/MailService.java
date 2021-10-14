
package com.proyecto.club.servicios;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author S
 */

@Service
public class MailService {
    
    @Autowired
    public JavaMailSenderImpl mailSender;
    
    
    public void enviarMail(String body, String title, String mail){
     
        SimpleMailMessage msj = new SimpleMailMessage();
        msj.setTo(mail);
        msj.setFrom("loscanuteros06@hotmail.com");
        msj.setSubject(title);
        msj.setText(body);
        
        mailSender.send(msj);
    }
    
}
