package com.example.solcoupang.product.controller;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.Seller;
import com.example.solcoupang.product.dto.product.ProductDto;
import com.example.solcoupang.product.dto.product.ProductRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/valid")
@Slf4j
public class ValidException {
    @PostMapping("/product")
    public ResponseEntity<ProductRequestDto> postProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        log.info("입력 dto 정보 : "+productRequestDto.getProductName()+" 첫 이미지 : "+productRequestDto.getProductContents().get(0));
        return ResponseEntity.status(HttpStatus.OK).body(productRequestDto);
    }

    @GetMapping("/exception")
    public void getRuntimeException(){
        throw new RuntimeException("getRuntimeException 호출");
    }
}
