package com.example.solcoupang.product.dto.review;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductOption;
import com.example.solcoupang.product.domain.Review;
import com.example.solcoupang.product.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
