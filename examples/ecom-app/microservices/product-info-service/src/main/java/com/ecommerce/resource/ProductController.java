package com.ecommerce.resource;

import com.ecommerce.model.entity.Product;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public Product getProductByProductId(@PathVariable Long productId) {
        return productService.getProductByProductId(productId);
    }

    @GetMapping("/recent-products/{categoryName}")
    public List<Product> getRecentProductsByCategoryName(@PathVariable String categoryName) {
        return productService.getRecentProductsByCategoryName(categoryName);
    }

    @GetMapping("/related-products/{productId}")
    public List<Product> getRelatedProductsByProductId(@PathVariable Long productId) {
        return productService.getRelatedProductsByProductId(productId);
    }

    @GetMapping("/product-name/{productId}")
    public String getProductNameByProductId(@PathVariable Long productId) {
        return productService.getProductNameByProductId(productId);
    }

    @GetMapping("/current-price/{productId}")
    public Integer getCurrentPriceByProductId(@PathVariable Long productId) {
        return productService.getCurrentPriceByProductId(productId);
    }




}
