package com.example.solcoupang.product.controller;

import com.example.solcoupang.product.service.ProductService;
import com.example.solcoupang.product.model.Delete.DeleteRes;
import com.example.solcoupang.product.model.Read.GetProductDetailRes;
import com.example.solcoupang.product.model.create.PostProductReq;
import com.example.solcoupang.product.model.create.PostProductRes;
import com.example.solcoupang.product.model.patch.PatchProductReq;
import com.example.solcoupang.product.model.patch.PatchProductRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/detail/")
@Slf4j
public class ProductControllerV1 {

    private final ProductService productService;

    @GetMapping(value = "/{productId}")
    public GetProductDetailRes getProductDetail(@PathVariable Long productId){
        return productService.getPoductDetail(productId);
    }

    @PostMapping("/item")
    public PostProductRes postProductDetail(@RequestBody PostProductReq postProductReq){
//        log.info(String.valueOf(postProductReq.getProductSimpleRes().getProductName()));
//        log.info(String.valueOf(postProductReq.getPostProductOptionList().get(0).getOptionTitle()));
        return productService.postProductDetail(postProductReq);
    }

    @PatchMapping("/fix")
    public PatchProductRes patchProductDetail(@RequestBody PatchProductReq patchProductReq){
        return productService.patchProductDetail(patchProductReq);
    }

    @DeleteMapping("/bin/{productId}")
    public DeleteRes deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }
}
