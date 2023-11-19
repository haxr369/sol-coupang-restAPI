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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_id")
    private List<Alarm> alarms;
}
