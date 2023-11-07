package com.example.solcoupang.product.dto.user;

import com.example.solcoupang.product.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long userId;
    private String userName;

    private String userPhoneNumber;

    private String userAddress;

    public UserDto(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userPhoneNumber = user.getUserPhoneNumber();
        this.userAddress = user.getUserAddress();
    }
}
