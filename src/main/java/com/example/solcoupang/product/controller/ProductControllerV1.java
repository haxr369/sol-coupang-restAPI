package com.example.solcoupang.product.controller;

import com.example.solcoupang.common.BaseResponse;
import com.example.solcoupang.product.dto.product.ProductDto;
import com.example.solcoupang.product.dto.product.ProductRequestDto;
import com.example.solcoupang.product.service.ProductService;
import com.example.solcoupang.product.model.Delete.DeleteRes;
import com.example.solcoupang.product.model.Read.GetProductDetailRes;
import com.example.solcoupang.product.model.create.PostProductReq;
import com.example.solcoupang.product.model.create.PostProductRes;
import com.example.solcoupang.product.model.patch.PatchProductReq;
import com.example.solcoupang.product.model.patch.PatchProductRes;
import com.example.solcoupang.product.service.ProductServiceV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/detail/")
@Slf4j
public class ProductControllerV1 {

    private final ProductService productService;
    private final ProductServiceV2 productServiceV2;

    @GetMapping(value = "/{productId}")
    public BaseResponse<ProductDto> getProductDetail(@PathVariable Long productId){
        ProductDto productDto = productServiceV2.findByProductId(productId);
        return new BaseResponse(productDto);
    }

    @PostMapping("/product")
    public ProductDto postProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        log.info("입력 dto 정보 : "+productRequestDto.getProductName()+" 첫 이미지 : "+productRequestDto.getProductContents().get(0));
        ProductDto savedProductDto = productServiceV2.saveProduct(productRequestDto);
        log.info("출력 dto 정보 id : "+savedProductDto.getSellerId() + " sellerId : "+savedProductDto.getSellerId() + " productName : "+savedProductDto.getProductName());
        return savedProductDto;
    }

    @PostMapping("/item")
    public BaseResponse<PostProductRes> postProductDetail(@RequestBody PostProductReq postProductReq){
//        log.info(String.valueOf(postProductReq.getProductSimpleRes().getProductName()));
//        log.info(String.valueOf(postProductReq.getPostProductOptionList().get(0).getOptionTitle()));
        PostProductRes postProductRes = productService.postProductDetail(postProductReq);
        return new BaseResponse<>(postProductRes);
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
