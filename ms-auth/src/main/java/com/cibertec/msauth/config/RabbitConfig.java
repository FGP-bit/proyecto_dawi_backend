package com.cibertec.msauth.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    
    @Bean
    public Queue loginQueue() {
        return new Queue("cola-login", true);
    }
}