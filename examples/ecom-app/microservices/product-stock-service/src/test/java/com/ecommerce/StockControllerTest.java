package com.ecommerce;

import com.ecommerce.model.StockDto;
import com.ecommerce.resource.StockController;
import com.ecommerce.service.StockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@WebMvcTest(StockController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Before
    public void setup() {
        Mockito.when(stockService.getStockListByProductId(42L)).thenReturn(prepareStockDtoList());
    }

    @Test
    public void test_getStockListByProductId() throws Exception {
        this.mockMvc.perform(get("/stock-list/42"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("DummySizeCode")));
    }

    private List<StockDto> prepareStockDtoList() {
        StockDto stockDto = new StockDto();
        stockDto.setSizeCode("DummySizeCode");
        stockDto.setProductId(21L);
        stockDto.setSizeId(7L);
        stockDto.setStock(192);
        return Arrays.asList(stockDto);
    }
}
