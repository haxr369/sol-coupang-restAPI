package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
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

    public ProductDto fromEntity(Product product){
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
                .build();
    }

}
