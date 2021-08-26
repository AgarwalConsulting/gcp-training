package com.ecommerce.service;

import com.ecommerce.model.Basket;
import com.ecommerce.model.BasketDto;
import com.ecommerce.model.BasketRepository;
import com.ecommerce.model.BasketSummaryDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ModelMapper modelMapper;

    // Only productIds & quantities
    public List<BasketSummaryDto> getBasketSummaryByUsername(String username) {
        // TODO: populate data.sql & parametrize
        return basketRepository.findBasketSummaryByUserId(1L)
                .stream()
                .map(summaryDto -> modelMapper.map(summaryDto, BasketSummaryDto.class))
                .collect(Collectors.toList());
    }

    // productIds & sizeIds & quantities
    public List<BasketDto> getDetailedBasketByUsername(String username) {
        // TODO: populate data.sql & parametrize
        return basketRepository.findAllByUserId(1L)
                .stream()
                .map(basket -> modelMapper.map(basket, BasketDto.class))
                .collect(Collectors.toList());
    }

    public Basket addToBasket(BasketDto basketDto) {
        Basket basket = modelMapper.map(basketDto, Basket.class);
        basket.setUserId(1L); // TODO: auth
        return basketRepository.save(basket);
    }

    @Transactional
    public void deleteFromBasket(Long productId, Long sizeId) {
        Long userId = 1L; // TODO: auth
        basketRepository.deleteByProductIdAndSizeIdAndUserId(productId, sizeId, userId);
    }

}
