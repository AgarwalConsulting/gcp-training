package com.ecommerce.service;

import com.ecommerce.model.Basket;
import com.ecommerce.model.BasketDto;
import com.ecommerce.model.BasketSummaryDto;

import java.util.List;

public interface BasketService {

    List<BasketSummaryDto> getBasketSummaryByUsername(String username);

    List<BasketDto> getDetailedBasketByUsername(String username);

    Basket addToBasket(BasketDto basketDto);

    void deleteFromBasket(Long productId, Long sizeId);

}
