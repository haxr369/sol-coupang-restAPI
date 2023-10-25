package com.example.solcoupang.product.model.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostProductOption {
    private Long price;
    private String optionTitle;
}
