package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.ProductContent;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 외부로부터 숨기고
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 오직 팩토리 메소드만 사용해서 객체를 만들수 있다.
public class ProductContentDto {

    private Long contentId;
    private Long productId;
    private String contentImgUrl;

    public static ProductContentDto fromEntity(ProductContent productContent){
        return ProductContentDto.builder()
                .contentId(productContent.getContentId())
                .productId(productContent.getProduct().getProductId())
                .contentImgUrl(productContent.getContentImgUrl())
                .build();
    }
}
