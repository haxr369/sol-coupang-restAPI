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
public class ProductContent {
    @Id // contentId는 프라빗키
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contentId;

    // LAZY, EAGER 로 했는데 왜 N+1 문제가 안 생기지?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id") // 외래키 지정
    private Product product;

    @Column
    private String contentImgUrl;

}
