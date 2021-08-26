package com.ecommerce.resource;

import com.ecommerce.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class SizeController {

    private final SizeService sizeService;

    @GetMapping("/size-code/{sizeId}")
    public String getSizeCodeBySizeId(@PathVariable Long sizeId) {
        return sizeService.getSizeCodeBySizeId(sizeId);
    }

}
