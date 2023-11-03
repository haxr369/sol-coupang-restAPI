package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.ProductOption;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 외부로부터 숨기고
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 오직 팩토리 메소드만 사용해서 객체를 만들수 있다.
public class ProductOptionDto {

    private Long optionId;
    private Long productId;
    private Long price;
    private String optionTitle;
    private String optionImgUrl;

    public static ProductOptionDto fromEntity(ProductOption productOption){
        return ProductOptionDto.builder()
                .optionId(productOption.getOptionId())
                .productId(productOption.getProduct().getProductId())
                .price(productOption.getPrice())
                .optionTitle(productOption.getOptionTitle())
                .optionImgUrl(productOption.getOptionImgUrl())
                .build();
    }

}
