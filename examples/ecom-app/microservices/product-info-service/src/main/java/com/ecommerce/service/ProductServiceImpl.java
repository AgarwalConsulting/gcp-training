package com.ecommerce.service;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.repository.CategoryRepository;
import com.ecommerce.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CacheManager cacheManager;

    @Cacheable(cacheNames = "getProduct") // evicted once an hour
    public Product getProductByProductId(Long productId) {
        return productRepository.findByProductId(productId);
    }

    @Cacheable(cacheNames = "getRecentProducts") // evicted once a minute
    public List<Product> getRecentProductsByCategoryName(String categoryName) {
        Long categoryId = categoryRepository.findByCategoryName(StringUtils.capitalize(categoryName)).getCategoryId();
        return productRepository.findFirst4ByCategoryIdOrderByProductIdDesc(categoryId);
    }

    @Cacheable(cacheNames = "getRelatedProducts") // evicted once an hour
    public List<Product> getRelatedProductsByProductId(Long productId) {
        Product product = productRepository.findByProductId(productId);
        return productRepository.findRelatedProducts(productId, product.getCategoryId(), product.getCurrentPrice());
    }

    public String getProductNameByProductId(Long productId) {
        return productRepository.findByProductId(productId).getProductName();
    }

    public Integer getCurrentPriceByProductId(Long productId) {
        return productRepository.findByProductId(productId).getCurrentPrice();
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void evictCachesEveryHour() {
        cacheManager.getCache("getProduct").clear();
        cacheManager.getCache("getRelatedProducts").clear();
    }

    @Scheduled(fixedRate = 60*1000)
    public void evictCachesEveryMinute() {
        cacheManager.getCache("getRecentProducts").clear();
    }

}
