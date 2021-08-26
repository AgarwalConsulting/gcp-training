package com.ecommerce.resource;

import com.ecommerce.model.entity.Product;
import com.ecommerce.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@RunWith(SpringRunner.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void test_getProductByProductId() throws Exception {
        Mockito.when(productService.getProductByProductId(1L)).thenReturn(prepareProductList().get(0));
        this.mockMvc.perform(get("/product/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("foo")));
    }

    @Test
    public void test_getRecentProductsByCategoryName() throws Exception {
        Mockito.when(productService.getRecentProductsByCategoryName("Suits")).thenReturn(prepareProductList());
        this.mockMvc.perform(get("/recent-products/Suits"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("foo")));
    }

    @Test
    public void test_getRelatedProductsByCategoryName() throws Exception {
        Mockito.when(productService.getRelatedProductsByProductId(1L)).thenReturn(prepareProductList());
        this.mockMvc.perform(get("/related-products/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("foo")));
    }

    @Test
    public void test_getProductNameByProductId() throws Exception {
        Mockito.when(productService.getProductNameByProductId(1L)).thenReturn("foo");
        this.mockMvc.perform(get("/product-name/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("foo")));
    }

    @Test
    public void test_getCurrentPriceByProductId() throws Exception {
        Mockito.when(productService.getCurrentPriceByProductId(1L)).thenReturn(392);
        this.mockMvc.perform(get("/current-price/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("392")));
    }

    private List<Product> prepareProductList() {
        Product product = new Product();
        product.setProductName("foo");
        product.setCurrentPrice(392);
        return Arrays.asList(product);
    }
}
