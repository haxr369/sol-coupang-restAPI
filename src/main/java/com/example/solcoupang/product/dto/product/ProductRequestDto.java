package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import com.example.solcoupang.product.domain.Seller;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class ProductRequestDto {
    private Long sellerId;
    private LocalDate registrationDate;
    private String productName;
    private String countryOfManufacture;
    private Long weight;
    private String kcCertificationInformation;
    private String manufacturer;
    private String importer;
    private List<String> productContentList;

    // DTO로 api 요청을 받고 이를 entity로 어떻게 바꾸지?

    public Product toEntity(Seller seller){
        return Product.builder()
                .seller(seller)
                .registrationDate(registrationDate)
                .productName(productName)
                .countryOfManufacture(countryOfManufacture)
                .weight(weight)
                .kcCertificationInformation(kcCertificationInformation)
                .manufacturer(manufacturer)
                .importer(importer)
                .productContents(productContentList.stream()
                        .map(content -> ProductContent.builder()
                                        .contentImgUrl(content).build())
                        .collect(Collectors.toList()))
                .build();
    }
}
