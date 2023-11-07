package com.example.solcoupang.product.dto.user;

import com.example.solcoupang.product.domain.Seller;
import com.example.solcoupang.product.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class UserRequestDto {
    private String userName;
    private String userPhoneNumber;
    private String userAddress;
    public User toEntity(){
        return User.builder()
                .userName(userName)
                .userPhoneNumber(userPhoneNumber)
                .userAddress(userAddress)
                .build();
    }
}
