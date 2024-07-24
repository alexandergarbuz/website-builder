package com.garbuz.web.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.garbuz.web.config.RabbitMQConfig;
import com.garbuz.web.model.Message;
import com.garbuz.web.service.EmailService;

import jakarta.mail.MessagingException;

@Component
public class QueueListener {
	private static final Logger LOG = LoggerFactory.getLogger(QueueListener.class);
    
	private final EmailService emailService;

    public QueueListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receiveMessage(final Message message) {
    	LOG.debug("Sending email to {} with {}", message.getToAddress(), message.getBody());
        try {
            emailService.sendEmail(message.getToAddress(), message.getSubject(), message.getBody());
		} catch (MessagingException e) {
			LOG.error("Cannot send email: {}", e);
		}
    }
}
