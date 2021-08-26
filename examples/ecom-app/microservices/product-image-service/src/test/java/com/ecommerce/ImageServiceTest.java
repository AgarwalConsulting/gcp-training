package com.ecommerce;

import com.ecommerce.model.Image;
import com.ecommerce.model.ImageRepository;
import com.ecommerce.service.ImageService;
import com.ecommerce.service.ImageServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    private ImageService imageService;

    @Before
    public void setup() {
        imageService = new ImageServiceImpl(imageRepository);
        Mockito.when(imageRepository.findAll()).thenReturn(prepareImageList());
    }

    @Test
    public void test_getImageByProductId() {
        final Image image = imageService.getImageByProductId(3L);
        Assert.assertEquals(Long.valueOf(1), image.getImageId());
        Assert.assertEquals("foo.bar", image.getImageUrl());
    }

    @Test
    public void test_getAllImagesByProductId() {
        final List<Image> imageList = imageService.getAllImagesByProductId(3L);
        Assert.assertEquals(Integer.valueOf(1), Integer.valueOf(imageList.size()));
        Assert.assertEquals(Long.valueOf(1), imageList.get(0).getImageId());
        Assert.assertEquals("foo.bar", imageList.get(0).getImageUrl());
    }

    private List<Image> prepareImageList() {
        Image image = new Image();
        image.setImageId(1L);
        image.setImageUrl("foo.bar");
        return Arrays.asList(image);
    }

}
