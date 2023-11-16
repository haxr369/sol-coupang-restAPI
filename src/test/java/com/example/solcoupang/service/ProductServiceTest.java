package com.example.solcoupang.service;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.Seller;
import com.example.solcoupang.product.dto.product.ProductDto;
import com.example.solcoupang.product.dto.product.ProductRequestDto;
import com.example.solcoupang.product.repository.ProductRepository;
import com.example.solcoupang.product.repository.SellerRepository;
import com.example.solcoupang.product.service.ProductService;
import com.example.solcoupang.product.service.ProductServiceV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ProductServiceTest { // 단위 테스트, 외부 연결 X

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private SellerRepository sellerRepository = Mockito.mock(SellerRepository.class);
    private ProductServiceV2 productService;

    @BeforeEach
    public void setUpTest(){ // ProductServiceV2 객체 생성을 위한 생성자를 통한 DI
        productService = new ProductServiceV2(productRepository, sellerRepository);
    }

    @Test
    void getProductByIdTest(){
        Seller seller = Seller.builder()
                .sellerId(1L)
                .build();

        Product givenProduct = Product.builder()
                .productId(1L)
                .seller(seller)
                .productName("삼애성플")
                .productContents(new ArrayList<>())
                .build();

        // Repository 객체의 동작을 가정한다.
        Mockito.when(productRepository.findByProductIdWithFetch(1L))
                .thenReturn(givenProduct);

        // Serivce 객체 결과값 얻기
        ProductDto productDto = productService.findByProductId(1L);

        // 결과값과 기대값 비교
        assertEquals(productDto.getProductId(), givenProduct.getProductId());
        assertEquals(productDto.getProductName(), givenProduct.getProductName());
        assertEquals(productDto.getSellerId(), givenProduct.getSeller().getSellerId());

        // 메서드 사용했는지 확인
        verify(productRepository).findByProductIdWithFetch(1L);
    }

    @Test
    void saveProductTest(){
        Seller seller = Seller.builder()
                .sellerId(1L)
                .build();
        // Repository 객체의 동작을 가정한다.
        Mockito.when(sellerRepository.findBySellerId(1L))
                .thenReturn(seller);

        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductDto productDto = productService.saveProduct(ProductRequestDto.builder()
                                                            .sellerId(1L)
                                                            .productName("삼애성플")
                                                            .productContents(new ArrayList<>())
                                                            .build());
        assertEquals(productDto.getProductName(), "삼애성플");
        assertEquals(productDto.getSellerId(), 1L);
        assertEquals(productDto.getProductContentDtoList(), new ArrayList<>());

        verify(productRepository).save(any());
    }
}
