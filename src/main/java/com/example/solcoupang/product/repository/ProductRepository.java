package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
