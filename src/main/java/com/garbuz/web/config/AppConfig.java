package com.garbuz.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties("application")
public class AppConfig implements WebMvcConfigurer {
	private String applicationName;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String name) {
		this.applicationName = name;
	}
	@Bean
	@Description("Spring Message Resolver")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
}