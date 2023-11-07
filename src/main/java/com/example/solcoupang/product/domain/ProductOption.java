package com.example.solcoupang.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductOption {

    @Id // productId를 프라빗키
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long optionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column
    private Long price;
    @Column
    private String optionTitle;
    @Column
    private String optionImgUrl;
}
