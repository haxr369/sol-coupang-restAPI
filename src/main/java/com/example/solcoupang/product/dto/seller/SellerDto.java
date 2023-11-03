package com.example.solcoupang.product.dto.seller;

import com.example.solcoupang.product.domain.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class SellerDto {

    private Long sellerId;
    private String sellerName;
    private String sellerPhoneNumber;
    private String sellerAddress;

    public SellerDto fromEntity(Seller seller){
        return SellerDto.builder()
                .sellerId(seller.getSellerId())
                .sellerName(seller.getSellerName())
                .sellerPhoneNumber(seller.getSellerPhoneNumber())
                .sellerAddress(seller.getSellerAddress())
                .build();
    }
}
