package com.ecommerce.service;

import com.ecommerce.model.Image;
import com.ecommerce.model.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    // findAll should be changed if dummy data (data.sql) is replaced by actual data
    public Image getImageByProductId(Long productId) { return imageRepository.findAll().get(0); }

    // findAll should be changed if dummy data (data.sql) is replaced by actual data
    public List<Image> getAllImagesByProductId(Long productId) {
        return imageRepository.findAll();
    }
}
