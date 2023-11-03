package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import com.example.solcoupang.product.domain.Seller;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 외부로부터 숨기고
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 오직 팩토리 메소드만 사용해서 객체를 만들수 있다.
public class ProductDto {
    private Long productId;
    private Long sellerId;
    private LocalDate registrationDate;
    private String productName;
    private String countryOfManufacture;
    private Long weight;
    private String kcCertificationInformation;
    private String manufacturer;
    private String importer;
    private List<String> productContentDtoList;
    // 객체 생성을 캡슐화하는 정적 팩토리 메서드
    public static ProductDto fromEntity(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .sellerId(product.getSeller().getSellerId())
                .registrationDate(product.getRegistrationDate())
                .productName(product.getProductName())
                .countryOfManufacture(product.getCountryOfManufacture())
                .weight(product.getWeight())
                .kcCertificationInformation(product.getKcCertificationInformation())
                .manufacturer(product.getManufacturer())
                .importer(product.getImporter())
                .productContentDtoList(product.getProductContents().stream().map(ProductContent::getContentImgUrl).toList())
                .build();

    }
}
