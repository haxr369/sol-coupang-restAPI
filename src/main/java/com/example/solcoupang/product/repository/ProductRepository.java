package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 일반적 join 사용
    @Query("SELECT p FROM Product p LEFT JOIN Seller s ON p.seller.sellerId = s.sellerId WHERE s.sellerName = :name ")
    Product findBySellerName(@Param("name") String name);

    // Fetch join 사용
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.seller WHERE p.productId = :id")
    Product findByProductIdWithFetch(@Param("id") Long id);

    Product findByProductId(Long id);

}
