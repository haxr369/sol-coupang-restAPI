package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findBySellerId(Long id);

}
