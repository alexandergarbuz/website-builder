package com.garbuz.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQProperties {
	@Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;
    
    @Value("${messaging.rabbitmq.protpcol}")
    private String protocol;
    
    @Value("${messaging.custom.ssl.enable}")
    private boolean customSslEnabled;

	public String getRabbitmqHost() {
		return rabbitmqHost;
	}

	public void setRabbitmqHost(String rabbitmqHost) {
		this.rabbitmqHost = rabbitmqHost;
	}

	public int getRabbitmqPort() {
		return rabbitmqPort;
	}

	public void setRabbitmqPort(int rabbitmqPort) {
		this.rabbitmqPort = rabbitmqPort;
	}

	public String getRabbitmqUsername() {
		return rabbitmqUsername;
	}

	public void setRabbitmqUsername(String rabbitmqUsername) {
		this.rabbitmqUsername = rabbitmqUsername;
	}

	public String getRabbitmqPassword() {
		return rabbitmqPassword;
	}

	public void setRabbitmqPassword(String rabbitmqPassword) {
		this.rabbitmqPassword = rabbitmqPassword;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public boolean isCustomSslEnabled() {
		return customSslEnabled;
	}

	public void setCustomSslEnabled(boolean customSslEnabled) {
		this.customSslEnabled = customSslEnabled;
	}
}
