package com.ecommerce.resource;

import com.ecommerce.model.StockDto;
import com.ecommerce.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock-list/{productId}")
    public List<StockDto> getStockListByProductId(@PathVariable Long productId) {
        return stockService.getStockListByProductId(productId);
    }

}
