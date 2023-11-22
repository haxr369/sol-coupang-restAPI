package com.example.solcoupang.product.controller;

import com.example.solcoupang.product.domain.ProductContent;
import com.example.solcoupang.product.dto.product.ProductContentDto;
import com.example.solcoupang.product.repository.ProductContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v2/productcontent")
@RequiredArgsConstructor
public class ProductContentController {

    private final ProductContentRepository productContentRepository;

    @GetMapping("/id/images")
    public List<ProductContentDto> getProductIdImages(@RequestParam Long id){
        List<ProductContent> productContentList = productContentRepository.findByProduct_ProductId(id);
        return productContentList.stream().map(
                productContent -> ProductContentDto.fromEntity(productContent)
        ).collect(Collectors.toList());
    }

    @GetMapping("/name/images")
    public List<ProductContentDto> getProductNameImages(@RequestParam String name){
        List<ProductContent> productContentList = productContentRepository.findByProduct_ProductName(name);
        return productContentList.stream().map(
                productContent -> ProductContentDto.fromEntity(productContent)
        ).collect(Collectors.toList());
    }
}
