package com.garbuz.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	public void addViewControllers(@NonNull ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("homePage");
		registry.addRedirectViewController("/", "home");
		//registry.addRedirectViewController("/", "/index.html");
	}
}
