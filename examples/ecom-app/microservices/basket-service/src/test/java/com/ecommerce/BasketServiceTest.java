package com.ecommerce;

import com.ecommerce.model.Basket;
import com.ecommerce.model.BasketDto;
import com.ecommerce.model.BasketRepository;
import com.ecommerce.service.BasketService;
import com.ecommerce.service.BasketServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    private BasketService basketService;

    @Before
    public void setup() {
        basketService = new BasketServiceImpl(basketRepository, new ModelMapper());
    }

    @Test
    public void test_getDetailedBasketByUsername() {
        Mockito.when(basketRepository.findAllByUserId(1L)).thenReturn(prepareBasketList());
        List<BasketDto> basketDtoList = basketService.getDetailedBasketByUsername("foo");
        Assert.assertEquals(Long.valueOf(7), basketDtoList.get(0).getProductId());
        Assert.assertEquals(Integer.valueOf(12), basketDtoList.get(0).getQuantity());
        Assert.assertEquals(Long.valueOf(1), basketDtoList.get(0).getSizeId());
    }

    private List<Basket> prepareBasketList() {
        Basket basket = new Basket();
        basket.setProductId(7L);
        basket.setQuantity(12);
        basket.setSizeId(1L);
        basket.setUserId(3L);
        return Arrays.asList(basket);
    }

}
