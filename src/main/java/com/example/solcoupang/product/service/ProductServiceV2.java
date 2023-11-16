package com.example.solcoupang.product.service;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.Seller;
import com.example.solcoupang.product.dto.product.ProductDto;
import com.example.solcoupang.product.dto.product.ProductRequestDto;
import com.example.solcoupang.product.repository.ProductRepository;
import com.example.solcoupang.product.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceV2 {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ProductDto findByProductId(Long id){
        Product product = productRepository.findByProductIdWithFetch(id);
        return ProductDto.fromEntity(product);
    }

    public ProductDto saveProduct(ProductRequestDto productRequestDto ){
        Seller seller = sellerRepository.findBySellerId(productRequestDto.getSellerId());
        Product product = productRequestDto.toEntity(seller); // 기본 상품 정보 저장
        product.addProductContent(productRequestDto.getProductContents());
        Product savedProduct = productRepository.save(product);
        log.info("엔티티 dto 정보 id : " + savedProduct.getProductId() +" productName : "+savedProduct.getProductName());
        return ProductDto.fromEntity(savedProduct);
    }
}
