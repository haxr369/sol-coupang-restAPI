package com.example.solcoupang.product.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.ArrayList;
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
//    @BatchSize(size=10)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    // Hibernate 쿼리는 content를 5개씩 가져올 것.
    private List<ProductContent> productContents = new ArrayList<>();

    // 연관관계 메서드 OneToMany에서는 One Entity에서 작성
    public void addProductContent(List<String> contents){
        for(String content : contents){
            productContents.add(ProductContent.builder()
                            .product(this)
                            .contentImgUrl(content)
                    .build());
        }
    }

//    public void setProductContents(List<ProductContent> productContents){
//        this.productContents = productContents;
//    }
    // 1:N에서 setter를 사용하는 것 보다
}



