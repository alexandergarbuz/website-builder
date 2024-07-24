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

    public static final String EMAIL_QUEUE = "emailQueue";

    private RabbitMQProperties rabbitMQProperties;
    
    public RabbitMQConfig(RabbitMQProperties rabbitMQProperties) {
    	this.rabbitMQProperties = rabbitMQProperties;
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
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
