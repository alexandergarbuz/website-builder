package com.garbuz.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.garbuz.web.controller.template.ContactUsTemplate;
import com.garbuz.web.model.ContactUsMessage;
import com.garbuz.web.util.FileUtils;


public class ContactUsTemplateTest {
	
	private ContactUsTemplate template;
	private ContactUsMessage message;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		this.message = new ContactUsMessage();
		this.message.setName("Alex Garbuz");
		this.message.setEmail("email@host.com");
		this.message.setPhone("1234567890");
		this.message.setMessage("message");
		
		this.template = new ContactUsTemplate(this.message);
	}

	@Test
	public void testProcess() throws Exception {
		
		final String path = "/email-templates/contact-us-email.html";
		final String pathToExcepted = "/email-templates-proccessed/contact-us-templated-proccesed.html";
		
		String output = this.template.process(path);
		String expectedOutput = FileUtils.readFile(pathToExcepted);
		
		Assertions.assertEquals(expectedOutput, output);
	}
}
