package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.ProductContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductContentRepository extends JpaRepository<ProductContent, Long> {

    List<ProductContent> findByProduct_ProductId(Long id);
    List<ProductContent> findByProduct_ProductName(String name);
}
