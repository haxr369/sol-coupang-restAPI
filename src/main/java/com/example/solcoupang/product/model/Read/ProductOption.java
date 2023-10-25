package com.example.solcoupang.product.model.Read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductOption {

    private Long optionId;
    private Long price;
    private String optionTitle;
}
