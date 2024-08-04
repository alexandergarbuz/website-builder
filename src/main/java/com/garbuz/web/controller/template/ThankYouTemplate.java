package com.garbuz.web.controller.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.garbuz.web.model.ContactUsMessage;
import com.garbuz.web.util.FileUtils;

public class ThankYouTemplate {

	private static String htmlTemplate;
	
	private TemplateProcessor processor;
	private String pathToTemplate;
	
	
	private static final String loadOnce(final String pathToTemplate) throws IOException {
		if(StringUtils.isBlank(htmlTemplate)) {
			htmlTemplate = FileUtils.readFile(pathToTemplate);
		}
		return htmlTemplate;
	}

	public ThankYouTemplate(final String pathToTemplate) {
		this.pathToTemplate  = pathToTemplate;
		this.processor = new TemplateProcessor();
	}

	public String process(final ContactUsMessage message) throws IOException {
		
		final String htmlTemplate = loadOnce(this.pathToTemplate);
		
		final Map<String, String> properties = new HashMap<>();
		properties.put(ContactUsEmailProps.NAME.getValue(), message.getName());
		
		return this.processor.processTemplate(htmlTemplate, properties);
	}
}
