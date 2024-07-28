package com.garbuz.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.garbuz.web.config.EmailConfig;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

	private JavaMailSender mailSender;
	private EmailConfig emailConfig;

	public EmailService(JavaMailSender mailSender, EmailConfig emailConfig) {
		this.mailSender = mailSender;
		this.emailConfig = emailConfig;
	}

	public void sendEmail(String to, String subject, String body) throws MessagingException {
		sendEmail(to, null, null,subject,body, false);

	}
	public void sendEmail(final String to, final String cc, final String bcc, final String subject, final String body, boolean isHtml) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(emailConfig.getUsername());
		helper.setTo(to);
		if(StringUtils.isNotBlank(cc)) {
			helper.setCc(cc);
		}
		if(StringUtils.isNotBlank(bcc)) {
			helper.setBcc(bcc);
		}
		
		helper.setSubject(subject);
		helper.setText(body, isHtml);
		mailSender.send(message);
		LOG.debug("Email sent to {}", to);

	}
}
