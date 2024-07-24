package com.garbuz.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("application")
public class ApplicationConfig {
	private String applicationName;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String name) {
		this.applicationName = name;
	}
}
