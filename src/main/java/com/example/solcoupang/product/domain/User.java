package com.example.solcoupang.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name="user_address")
    private String userAddress;

    // mappedBy는 연관관계 주인 엔티티의 필드를 작성
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Alarm> alarms;
}
