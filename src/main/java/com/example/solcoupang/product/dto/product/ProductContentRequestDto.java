package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductContentRequestDto {
    private Long productId;
    private List<String> contentImgUrl;

    public List<ProductContent> toEntities(Product product){
        return contentImgUrl.stream().map(
                content -> ProductContent.builder()
                        .product(product)
                        .contentImgUrl(content)
                        .build()
        ).collect(Collectors.toList());
    }
}
