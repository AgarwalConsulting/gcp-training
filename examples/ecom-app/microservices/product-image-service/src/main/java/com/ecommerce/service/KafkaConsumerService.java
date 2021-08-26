package com.ecommerce.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "checkout-topic")
    public void consumeMessage(String message){
        System.out.println(message);
    }

}

