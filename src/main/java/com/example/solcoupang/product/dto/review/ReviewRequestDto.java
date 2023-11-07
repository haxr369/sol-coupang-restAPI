package com.example.solcoupang.product.dto.review;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductOption;
import com.example.solcoupang.product.domain.Review;
import com.example.solcoupang.product.domain.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDto {
    private Integer starRate;
    private String comment;
    private String reviewImgUrl;
    private String summary;

    // 연관관계 메서드 ManyToOne은 의존하는 Entity를 주입 받아서 사용.
    public Review toEntity(User user, ProductOption productOption){
        return Review.builder()
                .user(user)
                .productOption(productOption)
                .starRate(starRate)
                .comment(comment)
                .reviewImgUrl(reviewImgUrl)
                .summary(summary)
                .build();
    }

}
