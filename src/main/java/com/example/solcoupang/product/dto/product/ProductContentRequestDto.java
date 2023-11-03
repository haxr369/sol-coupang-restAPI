package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductContentRequestDto {
    private Long productId;
    private String contentImgUrl;

    public ProductContent from(Product product){
        return ProductContent.builder()
                .product(product)
                .contentImgUrl(contentImgUrl)
                .build();
    }
}
