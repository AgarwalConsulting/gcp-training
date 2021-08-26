package com.ecommerce.service;

import com.ecommerce.model.SizeRepository;
import com.ecommerce.model.Stock;
import com.ecommerce.model.StockDto;
import com.ecommerce.model.StockRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    public List<StockDto> getStockListByProductId(Long productId) {
        List<StockDto> stockDtos = stockRepository.findAllByProductId(productId)
                .stream()
                .map(stock -> modelMapper.map(stock, StockDto.class))
                .collect(Collectors.toList());
        stockDtos.forEach(stockDto -> stockDto.setSizeCode(sizeRepository.findBySizeId(stockDto.getSizeId()).getSizeCode()));
        return stockDtos;
    }

}
