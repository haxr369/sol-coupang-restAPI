package com.example.solcoupang.product.model.Read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetProductDetailRes {
    private GetproductRes getproductRes;
    private List<ProductOption> productOptions;
    private List<ProductImage> productImages;
}
