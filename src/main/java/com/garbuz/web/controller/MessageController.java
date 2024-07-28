package com.garbuz.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.garbuz.web.config.EmailConfig;
import com.garbuz.web.model.ContactUsMessage;
import com.garbuz.web.model.ContactUsResponse;
import com.garbuz.web.model.Message;
import com.garbuz.web.service.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST})
public class MessageController {

	private Logger LOG = LoggerFactory.getLogger(MessageController.class);
	
	private MessageService messageService;
	private EmailConfig emailConfig;
	public MessageController(final MessageService messageService, final EmailConfig emailConfig) {
		this.messageService = messageService;
		this.emailConfig = emailConfig;
	}
	
	@PostMapping("/contact")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST})
	public ResponseEntity<ContactUsResponse> send(@RequestBody final ContactUsMessage messageToSend) {
		LOG.debug("Sending {} ", messageToSend);
		ContactUsResponse response = validateAndSend(messageToSend);
		
		if(!hasErrors(response.getErrors())) {
			try {
				
				StringBuilder bodyBuilder = new StringBuilder()
						.append("<p>Name: ").append(messageToSend.getName()).append("</p>")
						.append("<p>From: ").append(messageToSend.getEmail()).append("</p>")
						.append("<p>Phone: ").append(messageToSend.getPhone()).append("</p>")
						.append("<hr/>")
						.append(messageToSend.getMessage());
					
				
				
				Message contactUsEmailMessage = new Message();
				contactUsEmailMessage.setSubject("Contact Us Request");
				contactUsEmailMessage.setTo(emailConfig.getUsername());
				contactUsEmailMessage.setCc("alex@garbuz.com");
				contactUsEmailMessage.setBody(bodyBuilder.toString());
				contactUsEmailMessage.setHtml(true);
				messageService.sendContactUsMessage(contactUsEmailMessage);
				
				Message thankyouEmailMessage = new Message();
				thankyouEmailMessage.setTo(messageToSend.getEmail());
				thankyouEmailMessage.setSubject("Thank you");
				thankyouEmailMessage.setBody("Thank you for your message. We will get back to you shortly!");
				thankyouEmailMessage.setHtml(true);
				messageService.sendThankYouMessage(thankyouEmailMessage);
				
				
			} catch (Exception e) {
	            LOG.error("Error sending {} message. Error: {}", messageToSend, e);
	            response.getErrors().add("Email service is unavailable.");
	            response.setStatus(ContactUsResponse.ERROR);
			}			
		}
		LOG.debug("Sending response {}" ,response);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ContactUsResponse validateAndSend(final ContactUsMessage emailMessage) {
		LOG.debug("Validating {} message", emailMessage);
		
		String status = ContactUsResponse.SUCCESS;
		List<String> errors = new ArrayList<>();
		
		if(StringUtils.isBlank(emailMessage.getName())) {
			errors.add("Name cannot be blank");
		}
		if(!isEmailValid(emailMessage.getEmail())) {
			errors.add("Please enter valid email");
		}
		if(!isPhoneValid(emailMessage.getPhone())) {
			errors.add("Please use 10 digit phone number");
		}
		if(StringUtils.isBlank(emailMessage.getMessage())) {
			errors.add("Message cannot be blank");
		}		
		if(hasErrors(errors)) {
			status = ContactUsResponse.ERROR;
		}
		ContactUsResponse response = new ContactUsResponse(errors, status);
		LOG.debug("Validation results: {} ", response);		
		return response;
	}
	private boolean isPhoneValid(final String phoneNumber) {
        final String digitsOnly = phoneNumber.replaceAll("[^\\d]", "");
        return (StringUtils.length(digitsOnly) == 10);
	}
	private boolean isEmailValid(final String email) {
        final String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
	}

	private boolean hasErrors(final List<String> errors) {
		return CollectionUtils.isNotEmpty(errors);
	}
}
