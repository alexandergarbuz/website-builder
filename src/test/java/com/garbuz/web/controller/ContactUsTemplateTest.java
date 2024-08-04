package com.garbuz.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.garbuz.web.controller.template.ContactUsTemplate;
import com.garbuz.web.controller.template.ThankYouTemplate;
import com.garbuz.web.model.ContactUsMessage;
import com.garbuz.web.util.FileUtils;


public class ContactUsTemplateTest {
	
	private ContactUsTemplate contactUsTemplate;
	private ThankYouTemplate thankYouTemplate;
	private ContactUsMessage message;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		this.message = new ContactUsMessage();
		this.message.setName("Alex Garbuz");
		this.message.setEmail("email@host.com");
		this.message.setPhone("1234567890");
		this.message.setMessage("message");
		
		this.contactUsTemplate  = new ContactUsTemplate("/email-templates/contact-us-email.html");
		this.thankYouTemplate  = new ThankYouTemplate("/email-templates/thank-you-email.html");
	}

	@Test
	public void testProcess() throws Exception {
		String output = this.contactUsTemplate.process(message);
		String expectedOutput = FileUtils.readFile("/email-templates-proccessed/contact-us-templated-proccesed.html");
		
		Assertions.assertEquals(expectedOutput, output);
	}
	@Test
	public void testProcessThankYouEmail() throws Exception {
		String output = this.thankYouTemplate.process(message);
		String expectedOutput = FileUtils.readFile("/email-templates-proccessed/thank-you-email-proccessed.html");
		
		Assertions.assertEquals(expectedOutput, output);
	}
}
