package com.garbuz.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garbuz.web.config.RabbitMQConfig;
import com.garbuz.web.model.ContactUsMessage;
import com.garbuz.web.model.Message;

@Service
public class MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
	
    public boolean send(final Message messageToSend) {
    	LOG.debug("Putting message in {} queue {}", RabbitMQConfig.EMAIL_QUEUE, messageToSend);
    	rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, messageToSend);
		return true;
    }
    
    public boolean send(final ContactUsMessage messageToSend) {
    	LOG.debug("Putting message in {} queue {}", RabbitMQConfig.EMAIL_QUEUE, messageToSend);
    	return true;
    }
}
