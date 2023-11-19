package com.example.solcoupang.product.repository;


import com.example.solcoupang.product.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
