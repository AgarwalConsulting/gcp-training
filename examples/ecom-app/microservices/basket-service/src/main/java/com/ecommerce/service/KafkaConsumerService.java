package com.ecommerce.service;

import com.ecommerce.model.KafkaRowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final BasketService basketService;

    @KafkaListener(topics = "checkout-topic")
    public void deleteFromBasket(KafkaRowDto dto){
        basketService.deleteFromBasket(dto.getProductId(), dto.getSizeId());
    }

}
