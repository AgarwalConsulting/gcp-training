package com.ecommerce.service;

import com.ecommerce.model.KafkaRowDto;
import com.ecommerce.model.Stock;
import com.ecommerce.model.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final StockRepository stockRepository;

    @KafkaListener(topics = "checkout-topic")
    public void decreaseStock(KafkaRowDto dto){
        Stock stock = stockRepository.findByProductIdAndSizeId(dto.getProductId(), dto.getSizeId());
        Integer diff = stock.getStock() - dto.getQuantity();
        // TODO: Handle project-wide stock < 0 cases
        Integer newStock = diff >= 0 ? diff : 0;
        stock.setStock(newStock);
        stockRepository.save(stock);
    }

}

