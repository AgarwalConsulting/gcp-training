package com.ecommerce.resource;

import com.ecommerce.model.Image;
import com.ecommerce.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image/{productId}")
    public Image getImageByProductId(@PathVariable Long productId) {
        return imageService.getImageByProductId(productId);
    }

    @GetMapping("/image-list/{productId}")
    public List<Image> getAllImagesByProductId(@PathVariable Long productId) {
        return imageService.getAllImagesByProductId(productId);
    }

}

