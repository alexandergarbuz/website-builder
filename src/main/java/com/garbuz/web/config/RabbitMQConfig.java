package com.garbuz.web.config;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    public static final String THANK_YOU_QUEUE = "thankYouQueue";
    public static final String CONTACT_US_QUEUE = "contactUsQueue";

    private RabbitMQProperties rabbitMQProperties;
    
    public RabbitMQConfig(RabbitMQProperties rabbitMQProperties) {
    	this.rabbitMQProperties = rabbitMQProperties;
    }

    @Bean
    public Queue thankYouQueue() {
        return new Queue(THANK_YOU_QUEUE, true);
    }
    
    @Bean
    public Queue contactUsQueue() {
        return new Queue(CONTACT_US_QUEUE, true);
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory() throws KeyManagementException, NoSuchAlgorithmException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(rabbitMQProperties.getProtocol() + "://" + rabbitMQProperties.getRabbitmqHost() + ":" + rabbitMQProperties.getRabbitmqPort());
//        connectionFactory.setHost(rabbitMQProperties.getRabbitmqHost());
//        connectionFactory.setPort(rabbitMQProperties.getRabbitmqPort());
        connectionFactory.setUsername(rabbitMQProperties.getRabbitmqUsername());
        connectionFactory.setPassword(rabbitMQProperties.getRabbitmqPassword());

        if(rabbitMQProperties.isCustomSslEnabled()) {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, new java.security.SecureRandom());
            connectionFactory.getRabbitConnectionFactory().useSslProtocol(sslContext);        	
        }
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
