package com.example.solcoupang.product.controller;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import com.example.solcoupang.product.domain.Seller;
import com.example.solcoupang.product.dto.product.ProductContentDto;
import com.example.solcoupang.product.dto.product.ProductContentRequestDto;
import com.example.solcoupang.product.dto.product.ProductDto;
import com.example.solcoupang.product.dto.product.ProductRequestDto;
import com.example.solcoupang.product.dto.seller.SellerDto;
import com.example.solcoupang.product.dto.seller.SellerRequestDto;
import com.example.solcoupang.product.repository.ProductContentRepository;
import com.example.solcoupang.product.repository.ProductRepository;
import com.example.solcoupang.product.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v2/product")
public class ProductConrollerV2 {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final ProductContentRepository productContentRepository;

    @PostMapping("/seller")
    public SellerDto postSeller(@RequestBody SellerRequestDto sellerRequestDto) {
        log.info("입력 dto 정보 : " + sellerRequestDto.getSellerName());
        Seller seller = sellerRequestDto.toEntity();
        Seller savedSeller = sellerRepository.save(seller);
        log.info("엔티티 dto 정보 id : " + savedSeller.getSellerId() + " name : " + savedSeller.getSellerName());
        SellerDto sellerDto = new SellerDto(seller);
        log.info("출력 dto 정보 id : " + sellerDto.getSellerId() + " name : " + sellerDto.getSellerName());
        return sellerDto;
    }

    // Product를 만들기 위해선 seller 객체가 필요한데
    // findBySellerId 쿼리를 날려서 seller 객체를 받아와야하나? yes

    @PostMapping("/product")
    public ProductDto postProduct(@RequestBody ProductRequestDto productRequestDto) {
        log.info("입력 dto 정보 : "+productRequestDto.getProductName()+" 첫 이미지 : "+productRequestDto.getProductContents().get(0));
        Seller seller = sellerRepository.findBySellerId(productRequestDto.getSellerId());
        Product product = productRequestDto.toEntity(seller);

        Product savedProduct = productRepository.save(product);
        log.info("엔티티 dto 정보 id : " + savedProduct.getProductId() +" productName : "+savedProduct.getProductName());
        ProductDto productDto = ProductDto.fromEntity(savedProduct);
        log.info("출력 dto 정보 id : "+productDto.getSellerId() + " sellerId : "+productDto.getSellerId() + " productName : "+productDto.getProductName());
        return productDto;
    }

    @GetMapping("/withContents")
    public ProductDto getProductWithContents(@RequestParam Long productId){
        Product product = productRepository.findByProductId(productId);
        log.info("number of contents id1 : " + product.getProductContents().get(0).getContentId() + " id2 : " + product.getProductContents().get(1).getContentId());
        return ProductDto.fromEntity(product);
    }

    @GetMapping("/productWithName")
    public ProductDto getProductWithName( @RequestParam String name) {
        Product product = productRepository.findBySellerName(name);
        ProductDto productDto = ProductDto.fromEntity(product);
        log.info("productId : " + productDto.getProductId() + " sellerId :" + productDto.getSellerId() + " productName : " + productDto.getProductName());
        return productDto;
    }

    @GetMapping("/productFetch")
    public ProductDto getProductWithFetch(@RequestParam Long productId) {
        Product product = productRepository.findByProductIdWithFetch(productId);
        ProductDto productDto = ProductDto.fromEntity(product);
        log.info("productId : " + productDto.getProductId() + " sellerId :" + productDto.getSellerId() + " sellerName : " + product.getSeller().getSellerName() + " productName : " + productDto.getProductName());
        log.info("img url : "+product.getProductContents().get(0));
        return productDto;
    }

    @PostMapping("/product/content")
    public ProductDto postContent(@RequestBody ProductContentRequestDto productContentRequestDto) {
        Product product = productRepository.findByProductId(productContentRequestDto.getProductId());
        log.info("productContents n : " + product.getProductContents().size());
        List<ProductContent> productContentList = productContentRequestDto.toEntities(product);
        productContentRepository.saveAll(productContentList);
        return ProductDto.fromEntity(product);
    }

}