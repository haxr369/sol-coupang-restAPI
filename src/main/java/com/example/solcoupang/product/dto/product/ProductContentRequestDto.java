package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductContentRequestDto {
    private Long productId;
    private String contentImgUrl;

    public ProductContent toEntity(Product product){
        return ProductContent.builder()
                .product(product)
                .contentImgUrl(contentImgUrl)
                .build();
    }
}
