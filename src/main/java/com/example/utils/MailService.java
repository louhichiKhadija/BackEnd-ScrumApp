package com.example.utils;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.entities.ConfirmationToken;
import com.example.entities.User;

@Transactional
@Service("mailService")
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendResetEmail(User user,ConfirmationToken confirmationToken) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Reset password");
		mail.setText("To reset your password, please click here:"
				+ "http://localhost:4200/auth/reset-password?token="+confirmationToken.getConfirmationToken());
		javaMailSender.send(mail);
		
	}
	
	public void sendConfirmationEmail(User user, ConfirmationToken confirmationToken) throws MailException {
		
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:4200/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
        javaMailSender.send(mailMessage);		
	}



}