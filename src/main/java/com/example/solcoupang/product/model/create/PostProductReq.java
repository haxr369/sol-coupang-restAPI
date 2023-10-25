package com.example.solcoupang.product.model.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostProductReq {
    private ProductSimpleRes productSimpleRes;
    private List<PostProductOption> postProductOptionList;
    private List<String> productImgUrls;
}
