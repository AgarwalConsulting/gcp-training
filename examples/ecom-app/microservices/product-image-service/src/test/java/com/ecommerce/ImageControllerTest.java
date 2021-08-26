package com.ecommerce;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerce.model.Image;
import com.ecommerce.resource.ImageController;
import com.ecommerce.service.ImageService;
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

@WebMvcTest(ImageController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @Before
    public void setup() {
        Mockito.when(imageService.getImageByProductId(92L)).thenReturn(prepareImageList().get(0));
        Mockito.when(imageService.getAllImagesByProductId(3L)).thenReturn(prepareImageList());
    }

    @Test
    public void test_getImageByProductId() throws Exception {
        this.mockMvc.perform(get("/image/92"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foo.bar")));
    }

    @Test
    public void test_getAllImagesByProductId() throws Exception {
        this.mockMvc.perform(get("/image-list/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foo.bar")));
    }

    private List<Image> prepareImageList() {
        Image image = new Image();
        image.setImageId(1L);
        image.setImageUrl("foo.bar");
        return Arrays.asList(image);
    }

}
