package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.service.SizeService;
import com.ecommerce.service.SizeServiceImpl;
import com.ecommerce.service.StockService;
import com.ecommerce.service.StockServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SizeServiceTest {

    @Mock
    private SizeRepository sizeRepository;

    private SizeService sizeService;

    @Before
    public void setup() {
        sizeService = new SizeServiceImpl(sizeRepository);
        Mockito.when(sizeRepository.findBySizeId(2L)).thenReturn(prepareSize());
    }

    @Test
    public void test_getSizeCodeBySizeId() {
        String sizeCode = sizeService.getSizeCodeBySizeId(2L);
        Assert.assertEquals("M", sizeCode);
    }

    private Size prepareSize() {
        Size size = new Size();
        size.setSizeId(2L);
        size.setSizeCode("M");
        return size;
    }

}
