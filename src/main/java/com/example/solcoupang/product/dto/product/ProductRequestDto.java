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
import java.util.ArrayList;
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
    private List<String> productContents;

    // DTO로 api 요청을 받고 이를 entity로 어떻게 바꾸지?

    public Product toEntity(Seller seller){
        // 1대 N을 위해 굳이 product를 만들다만다.
        Product product = Product.builder()
                .seller(seller)
                .registrationDate(registrationDate)
                .productName(productName)
                .countryOfManufacture(countryOfManufacture)
                .weight(weight)
                .kcCertificationInformation(kcCertificationInformation)
                .manufacturer(manufacturer)
                .importer(importer)
                .build();
        // ProductContent의 외래키를 위해 만들다만 product를 리스트로 만들어서 넣어준다.
        List<ProductContent> productContentList = productContents.stream()
                .map(content ->
                        ProductContent
                                .builder()
                                .product(product)
                                .contentImgUrl(content)
                                .build())
                .collect(Collectors.toList());

        // 1대N 관계에서 엔티티를 만들기 위해 결국 set 함수를 사용하게 된다.
        product.setProductContents(productContentList);
        return product;
    }


}
