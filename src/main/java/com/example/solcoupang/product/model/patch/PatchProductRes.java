package com.example.solcoupang.product.model.patch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatchProductRes {
    private Long productId;
    private String productName;
}
