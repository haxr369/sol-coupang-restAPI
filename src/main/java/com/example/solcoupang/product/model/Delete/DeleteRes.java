package com.example.solcoupang.product.model.Delete;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRes {
    private Integer removedImages;
    private Integer removedOptions;
    private Long productId;


    public DeleteRes(Long productId) {
        this.productId = productId;
    }
}
