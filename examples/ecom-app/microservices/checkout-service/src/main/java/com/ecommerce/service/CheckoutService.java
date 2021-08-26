package com.ecommerce.service;

import com.ecommerce.model.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final SaleRepository saleRepository;
    private final SalesDetailRepository salesDetailRepository;

    public boolean checkout(FormDto formDto, List<BasketRowDto> basketRowDtoList) {

        boolean isPaymentSuccessful = tryPayment(formDto.getCcn());

        if(!isPaymentSuccessful) {
            return false;
        }

        // 1. Update sale table
        Sale sale = new Sale();
        sale.setUserId(1L); // TODO: Auth
        sale.setOrderDate(LocalDateTime.now());
        Long saleId = saleRepository.save(sale).getSaleId();

        basketRowDtoList.forEach(row -> {

            // 2. Update sales_detail table
            SalesDetail salesDetail = new SalesDetail();
            salesDetail.setSaleId(saleId);
            salesDetail.setProductId(row.getProductId());
            salesDetail.setQuantity(row.getQuantity());
            salesDetail.setSizeId(row.getSizeId());
            salesDetail.setItemPrice(row.getCurrentPrice());
            salesDetailRepository.save(salesDetail);

            // 3. Publish Kafka message to be consumed by product-stock-service and basket-service


        });

        return true;
    }

    private boolean tryPayment(String ccn) {
        // In a real-world scenario, this would be a microservice of its own
        // However, payment operations are out of the scope of this project
        return ccn.replaceAll(" ", "").equals("1234123412341234");
    }

}
