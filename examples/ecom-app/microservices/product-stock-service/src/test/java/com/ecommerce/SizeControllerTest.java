package com.ecommerce;

import com.ecommerce.model.Size;
import com.ecommerce.resource.SizeController;
import com.ecommerce.service.SizeService;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SizeController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class SizeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SizeService sizeService;

    @Before
    public void setup() {
        Mockito.when(sizeService.getSizeCodeBySizeId(2L)).thenReturn(prepareSize());
    }

    @Test
    public void test_getSizeCodeBySizeId() throws Exception {
        this.mockMvc.perform(get("/size-code/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("M")));
    }

    private String prepareSize() {
        Size size = new Size();
        size.setSizeId(2L);
        size.setSizeCode("M");
        return size.getSizeCode();
    }

}
