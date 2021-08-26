package com.ecommerce.service;

import com.ecommerce.model.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    public String getSizeCodeBySizeId(Long sizeId) {
        return sizeRepository.findBySizeId(sizeId).getSizeCode();
    }

}
