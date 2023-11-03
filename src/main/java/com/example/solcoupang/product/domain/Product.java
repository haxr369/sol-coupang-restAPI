package com.example.solcoupang.product.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {

    @Id // productId를 프라빗키
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY) // 실무에선 Lazy 사용
    @JoinColumn(name = "sellerId") // sellerId가 외래키
    private Seller seller;

    private LocalDate registrationDate;

    private String productName;

    private String countryOfManufacture;

    private Long weight;

    private String kcCertificationInformation;

    private String manufacturer;

    private String importer;
}



