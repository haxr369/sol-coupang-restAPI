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

    // 커스텀 생성자
    public SellerDto(Seller seller){
        this.sellerId = seller.getSellerId();
        this.sellerName = seller.getSellerName();
        this.sellerPhoneNumber = seller.getSellerPhoneNumber();
        this.sellerAddress = seller.getSellerAddress();
    }
}
