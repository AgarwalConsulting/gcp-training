package com.ecommerce.resource;

import com.ecommerce.model.Basket;
import com.ecommerce.model.BasketDto;
import com.ecommerce.model.BasketSummaryDto;
import com.ecommerce.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    // productIds & quantities
    @GetMapping("/basket-summary/{username}")
    public List<BasketSummaryDto> getSummaryBasketByUsername(@PathVariable String username) {
        return basketService.getBasketSummaryByUsername(username);
    }

    // productIds & sizeIds & quantities
    @GetMapping("/detailed-basket/{username}")
    public List<BasketDto> getDetailedBasketByUsername(@PathVariable String username) {
        return basketService.getDetailedBasketByUsername(username);
    }

    @PostMapping("/add-to-basket")
    public Basket addToBasket(@RequestBody BasketDto basketDto) {
        return basketService.addToBasket(basketDto);
    }

    @DeleteMapping("/delete-from-basket/{productId}/{sizeId}")
    public ResponseEntity<String> deleteFromBasket(@PathVariable Long productId, @PathVariable Long sizeId) {
        basketService.deleteFromBasket(productId, sizeId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
