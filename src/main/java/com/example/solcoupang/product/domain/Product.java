package com.example.solcoupang.product.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {

    @Id
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

    // product 저장 때 content도 함께 저장하는 쿼리 생성
    // mappedBy 속성으로 관계의 소유자 설정
    // productId를 프라빗키
    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ProductContent> productContents;

    public void setProductContents(List<ProductContent> productContents){
        this.productContents = productContents;
    }
}



