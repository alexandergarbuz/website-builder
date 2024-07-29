package com.garbuz.web.controller.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.garbuz.web.model.ContactUsMessage;

public class ContactUsTemplate {

	private ContactUsMessage message;
	private TemplateProcessor processor;

	public ContactUsTemplate(ContactUsMessage message) {
		this.processor = new TemplateProcessor();
		this.message = message;
	}

	public String process(final String pathToTemplate) throws IOException {
		
		final Map<String, String> properties = new HashMap<>();
		properties.put(EmailProps.EMAIL.getValue(), this.message.getEmail());
		properties.put(EmailProps.PHONE.getValue(), this.message.getPhone());
		properties.put(EmailProps.NAME.getValue(), this.message.getName());
		properties.put(EmailProps.BODY.getValue(), this.message.getMessage());
		
		return this.processor.processTemplate(pathToTemplate, properties);
	}
	
	public enum EmailProps {
	    NAME("NAME"),
	    PHONE("PHONE"),
	    EMAIL("EMAIL"),
	    BODY("BODY");

	    private final String value;

	    EmailProps(String placeholder) {
	        this.value = placeholder;
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
}
