package br.com.evisas.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);
	
	@Autowired
    public JavaMailSender mailSender;
	
	@Override
	public boolean enviarEmailSimples(String destinatario, String assunto, String texto) {
		try {
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(destinatario); 
	        message.setSubject(assunto); 
	        message.setText(texto);
	        
	        mailSender.send(message);
	        logger.info("Email enviado: [destinatario=" + destinatario + ", assunto=" + assunto + "]");
	        return true;
	        
		} catch (MailSendException e) {
			logger.error("Erro ao enviar email: [destinatario=" + destinatario + ", assunto=" + assunto + "]");
			return false;
		}
	}
}
