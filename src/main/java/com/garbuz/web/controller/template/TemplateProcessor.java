package com.garbuz.web.controller.template;

import java.util.Map;


public class TemplateProcessor {
	
    public String processTemplate(final String htmlTemplate, final Map<String, String> templateProperties)  {
        String html = htmlTemplate;
        for (Map.Entry<String, String> entry : templateProperties.entrySet()) {
        	html = html.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return html; 
    }

}
