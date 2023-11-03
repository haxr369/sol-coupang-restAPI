package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
