package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.service.StockService;
import com.ecommerce.service.StockServiceImpl;
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
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private SizeRepository sizeRepository;

    private StockService stockService;

    @Before
    public void setup() {
        stockService = new StockServiceImpl(stockRepository, sizeRepository, new ModelMapper());
        Mockito.when(stockRepository.findAllByProductId(42L)).thenReturn(prepareStockDtoList());
        Mockito.when(sizeRepository.findBySizeId(2L)).thenReturn(prepareSize());
    }

    @Test
    public void test_getAllImagesByProductId() {
        final List<StockDto> stockDtoList = stockService.getStockListByProductId(42L);
        Assert.assertEquals(Integer.valueOf(1), Integer.valueOf(stockDtoList.size()));
        Assert.assertEquals(Long.valueOf(21), stockDtoList.get(0).getProductId());
        Assert.assertEquals(Long.valueOf(2), stockDtoList.get(0).getSizeId());
        Assert.assertEquals("M", stockDtoList.get(0).getSizeCode());
        Assert.assertEquals(Integer.valueOf(192), stockDtoList.get(0).getStock());
    }

    private List<Stock> prepareStockDtoList() {
        Stock stock = new Stock();
        stock.setProductId(21L);
        stock.setSizeId(2L);
        stock.setStock(192);
        return Arrays.asList(stock);
    }

    private Size prepareSize() {
        Size size = new Size();
        size.setSizeId(2L);
        size.setSizeCode("M");
        return size;
    }
}
