package com.lqg.util;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;



public class EmailUtil {
	@Autowired
	//注入JavaMailsender
	private JavaMailSender mailSender;
	public  String doSend(MessageInfo msg){				
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = null;
		try{
			messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");		
			messageHelper.setTo(msg.getTo());
			messageHelper.setSubject(msg.getSubject());
			messageHelper.setSentDate(msg.getSendDate());
			messageHelper.setText(msg.getMsg(),true);
			JavaMailSenderImpl sender = (JavaMailSenderImpl)mailSender;	
			sender.send(mimeMessage);
			return "邮件正常发送";
		}catch(Exception ex){
			ex.printStackTrace();
			return "邮件发送失败";
		}	
	}
}
