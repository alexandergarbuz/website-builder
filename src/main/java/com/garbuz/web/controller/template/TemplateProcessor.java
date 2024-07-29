package com.garbuz.web.controller.template;

import java.io.IOException;
import java.util.Map;

import com.garbuz.web.util.FileUtils;


public class TemplateProcessor {
	
    public String processTemplate(final String templatePath, final Map<String, String> templateProperties) throws IOException {
        String html = FileUtils.readFile(templatePath);
        for (Map.Entry<String, String> entry : templateProperties.entrySet()) {
        	html = html.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return html; 
    }

}
