package com.mortenporten.dugnad.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.mortenporten.dugnad.core.persistence.Person;

@Async
@Service("dutyInformationMail")
public class DutyInformationMail {
	
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	VelocityEngine velocityEngine;
	
	
	public void sendMail(String person){
		MimeMessage message = mailSender.createMimeMessage();

	
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo("morteniporten@gmail.com");
            helper.setFrom("morten.hoeiland@gmail.com"); 
            Map model = new HashMap();
            model.put("person", person);
            String text = VelocityEngineUtils.mergeTemplateIntoString(
               velocityEngine, "/vm/hello.vm", model);
            helper.setText(text, true);
            
			
            mailSender.send(helper.getMimeMessage());
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
