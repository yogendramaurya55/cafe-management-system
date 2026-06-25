package com.project.cafe.utils;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailUtils {
	
	private final JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to , String subject , String body , List<String> list  ) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("yogendramaurya1709@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		if(list != null && list.size() > 0) {
			message.setCc(getCcArray(list));
		}
		
		emailSender.send(message);
	}
	
	private String[] getCcArray(List<String> ccList) {
		String[] cc = new String[ccList.size()];
		
		for(int i=0; i<cc.length; i++) {
			cc[i] = ccList.get(i);
		}
		
		return cc;
	}
	
}
