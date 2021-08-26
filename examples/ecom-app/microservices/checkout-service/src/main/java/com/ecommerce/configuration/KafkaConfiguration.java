package com.ecommerce.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.checkout.topic}")
    private String CHECKOUT_TOPIC;

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(CHECKOUT_TOPIC,3,(short) 1);
    }

}
