package com.garbuz.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garbuz.web.config.RabbitMQConfig;
import com.garbuz.web.model.Message;

@Service
public class MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
	
    public boolean sendContactUsMessage(final Message messageToSend) {
    	LOG.debug("Putting message in {} queue {}", RabbitMQConfig.CONTACT_US_QUEUE, messageToSend);
    	rabbitTemplate.convertAndSend(RabbitMQConfig.CONTACT_US_QUEUE, messageToSend);
		return true;
    }
    
    public boolean sendThankYouMessage(final Message messageToSend) {
    	LOG.debug("Putting message in {} queue {}", RabbitMQConfig.THANK_YOU_QUEUE, messageToSend);
    	rabbitTemplate.convertAndSend(RabbitMQConfig.THANK_YOU_QUEUE, messageToSend);
		return true;
    }
}
