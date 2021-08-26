package com.ecommerce.service;

import com.ecommerce.model.entity.Category;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.repository.CategoryRepository;
import com.ecommerce.model.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cache.CacheManager;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CacheManager cacheManager;

    @Before
    public void setup() {
        productService = new ProductServiceImpl(productRepository, categoryRepository, cacheManager);
    }

    @Test
    public void test_getProductByProductId() {
        Mockito.when(productRepository.findByProductId(201L)).thenReturn(prepareProductList().get(0));
        final Product dto = productService.getProductByProductId(201L);
        Assert.assertEquals(Long.valueOf(1), dto.getProductId());
        Assert.assertEquals("foo", dto.getProductName());
        Assert.assertEquals(Long.valueOf(2), dto.getCategoryId());
        Assert.assertEquals(Integer.valueOf(42), dto.getCurrentPrice());
    }

    @Test
    public void test_getRecentProductsByCategoryName() {
        Mockito.when(categoryRepository.findByCategoryName("Suits")).thenReturn(prepareCategory());
        Mockito.when(productRepository.findFirst4ByCategoryIdOrderByProductIdDesc(2L)).thenReturn(prepareProductList());
        final List<Product> suits = productService.getRecentProductsByCategoryName("Suits");
        Assert.assertEquals(Integer.valueOf(42), suits.get(0).getCurrentPrice());
    }

    @Test
    public void test_getRelatedProductsByProductId() {
        Mockito.when(productRepository.findByProductId(1L)).thenReturn(prepareProductList().get(0));
        Mockito.when(productRepository.findRelatedProducts(1L, 2L, 42)).thenReturn(prepareProductList());
        final List<Product> rp = productService.getRelatedProductsByProductId(1L);
        Assert.assertEquals(Integer.valueOf(42), rp.get(0).getCurrentPrice());
    }

    @Test
    public void test_getProductNameByProductId() {
        Mockito.when(productRepository.findByProductId(5L)).thenReturn(prepareProductList().get(0));
        final String productName = productService.getProductNameByProductId(5L);
        Assert.assertEquals("foo", productName);
    }

    @Test
    public void test_getCurrentPriceByProductId() {
        Mockito.when(productRepository.findByProductId(7L)).thenReturn(prepareProductList().get(0));
        final Integer currentPrice = productService.getCurrentPriceByProductId(7L);
        Assert.assertEquals(Integer.valueOf(42), currentPrice);
    }

    private List<Product> prepareProductList() {
        final Product product = new Product();
        product.setProductId(1L);
        product.setProductName("foo");
        product.setCategoryId(2L);
        product.setCurrentPrice(42);
        return Lists.newArrayList(product);
    }

    private Category prepareCategory() {
        final Category category = new Category();
        category.setCategoryId(2L);
        category.setCategoryName("Suits");
        return category;
    }

}
