package com.ecommerce;

import com.ecommerce.model.BasketDto;
import com.ecommerce.model.BasketSummaryDto;
import com.ecommerce.resource.BasketController;
import com.ecommerce.service.BasketService;
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

@WebMvcTest(BasketController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @Test
    public void test_getSummaryBasketByUsername() throws Exception {
        Mockito.when(basketService.getBasketSummaryByUsername("foo")).thenReturn(prepareBasketSDtoList());
        this.mockMvc.perform(get("/basket-summary/foo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("612")));
    }

    @Test
    public void test_getDetailedBasketByUsername() throws Exception {
        Mockito.when(basketService.getDetailedBasketByUsername("foo")).thenReturn(prepareBasketDtoList());
        this.mockMvc.perform(get("/detailed-basket/foo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("306")));
    }

    private List<BasketDto> prepareBasketDtoList() {
        BasketDto dto = new BasketDto();
        dto.setProductId(4L);
        dto.setQuantity(306);
        dto.setSizeId(2L);
        return Arrays.asList(dto);
    }

    private List<BasketSummaryDto> prepareBasketSDtoList() {
        BasketSummaryDto dto = new BasketSummaryDto();
        dto.setProductId(3L);
        dto.setQuantity(612);
        return Arrays.asList(dto);
    }

}
