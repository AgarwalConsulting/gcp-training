package com.ecommerce.service;

import com.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductByProductId(Long productId);

    List<Product> getRecentProductsByCategoryName(String categoryName);

    List<Product> getRelatedProductsByProductId(Long productId);

    String getProductNameByProductId(Long productId);

    Integer getCurrentPriceByProductId(Long productId);

}
