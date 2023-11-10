package com.example.solcoupang.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    @Column
    private String sellerName;
    @Column
    private String sellerPhoneNumber;
    @Column
    private String sellerAddress;

}
