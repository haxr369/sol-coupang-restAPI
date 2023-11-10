package com.example.solcoupang.product.dto.seller;

import com.example.solcoupang.product.domain.Seller;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class SellerRequestDto {
    private String sellerName;
    private String sellerPhoneNumber;
    private String sellerAddress;
    public Seller toEntity(){
        return Seller.builder()
                .sellerName(sellerName)
                .sellerPhoneNumber(sellerPhoneNumber)
                .sellerAddress(sellerAddress)
                .build();
    }
}
