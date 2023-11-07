package com.example.solcoupang.product.dto.review;

import com.example.solcoupang.product.domain.Review;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 외부로부터 숨기고
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto {
    private Long reviewId;
    private Long userId;
    private Long productOptionId;
    private Integer starRate;
    private String comment;
    private String reviewImgUrl;
    private String summary;

    public static ReviewDto fromEntity(Review review){
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .productOptionId(review.getProductOption().getOptionId())
                .userId(review.getUser().getUserId())
                .starRate(review.getStarRate())
                .comment(review.getComment())
                .reviewImgUrl(review.getReviewImgUrl())
                .summary(review.getSummary())
                .build();
    }
}
