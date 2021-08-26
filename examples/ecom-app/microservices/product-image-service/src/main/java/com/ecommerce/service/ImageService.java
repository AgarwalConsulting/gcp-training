package com.ecommerce.service;

import com.ecommerce.model.Image;

import java.util.List;

public interface ImageService {

    List<Image> getAllImagesByProductId(Long productId);

    Image getImageByProductId(Long productId);
}
