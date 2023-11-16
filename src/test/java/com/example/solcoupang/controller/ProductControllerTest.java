package com.example.solcoupang.controller;

import com.example.solcoupang.product.controller.ProductControllerV1;
import com.example.solcoupang.product.dto.product.ProductDto;
import com.example.solcoupang.product.service.ProductService;
import com.example.solcoupang.product.service.ProductServiceV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductControllerV1.class) //슬라이스 테스트
//@RequiredArgsConstructor
public class ProductControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    ProductServiceV2 productService;

    @Autowired
    public ProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("Mock MVC를 통한 Product 데이터 가져오기 테스트")
    void getProductDetail() throws Exception{

        given(productService.findByProductId(1L))
                .willReturn(
                        ProductDto.builder().productId(1L)
                                .productName("test1")
                                .sellerId(1L)
                                .registrationDate(LocalDate.parse("2023-11-16"))
                                .countryOfManufacture("한국")
                                .weight(1000L)
                                .kcCertificationInformation("한국 : 응! 인정!")
                                .manufacturer("당근")
                                .importer("한국물류")
                                .productContentDtoList(List.of("https:s3//djsfdl","https:s3//djsfdl","https:s3//djsfdl"))
                                .build()
                );

        String productId = "1";

        mockMvc.perform(
                get("/api/v1/products/detail/"+productId)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").exists())
                .andExpect(jsonPath("$.result.productId").exists())
                .andExpect(jsonPath("$.result.sellerId").exists())
                .andDo(print());

        verify(productService).findByProductId(1L);
    }
}
