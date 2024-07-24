package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garbuz.web.model.Message;
import com.garbuz.web.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	private Logger LOG = LoggerFactory.getLogger(MessageController.class);
	
	private MessageService messageService;
	
	public MessageController(final MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<Message> send(@RequestBody final Message messageToSend) {
		LOG.debug("Sending {} ", messageToSend);
		messageService.send(messageToSend);
		return new ResponseEntity<>(messageToSend, HttpStatus.OK);
	}
}
