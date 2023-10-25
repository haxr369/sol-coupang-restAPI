package com.example.solcoupang.product.model.Read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductImage {
    private Long contentId;
    private String contentImgUrl;
}
