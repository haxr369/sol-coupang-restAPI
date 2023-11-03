package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionRequestDto {
    private Long productId;
    private Long price;
    private String optionTitle;
    private String optionImgUrl;

    public ProductOption from(Product product){
        return ProductOption.builder()
                .product(product)
                .price(price)
                .optionTitle(optionTitle)
                .optionImgUrl(optionImgUrl)
                .build();
    }
}
