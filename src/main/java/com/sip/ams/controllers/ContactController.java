package com.sip.ams.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
	@Autowired
	private JavaMailSender mailSender ; 
	
	
	@GetMapping("/contact")
	public String showContactForm() {
		
		return "contactForm";
	}
	
	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request) {
	
		String fullName = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		SimpleMailMessage message = new SimpleMailMessage();
		//message.setFrom("zorgani10.med@gmail.com");
		message.setTo("mohamed01.zorgani@gmail.com");
		String mailSubject =fullName+ " has sent a message ";
		String mailContent = "Sender Name: "+ fullName+" \n";
		mailContent += "Sender email: "+ email+" \n";
		mailContent += "Subject: "+ subject+" \n";
		mailContent += "Content: "+ content+" \n";
		message.setSubject(mailSubject);
		message.setText(mailContent); 
		
		mailSender.send(message);	
		
		return "redirect:home";
	}
	

}
