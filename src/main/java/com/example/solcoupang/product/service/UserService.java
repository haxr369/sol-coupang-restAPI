package com.example.solcoupang.product.service;

import com.example.solcoupang.product.domain.Alarm;
import com.example.solcoupang.product.domain.User;
import com.example.solcoupang.product.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void userAlarmInsert(){
        User user1 = new User(null,"오솔","010-6604-4461","집수소1",new ArrayList<>());

        Alarm alarm1 = new Alarm(null,user1,"기다려오 아저시",false, LocalDateTime.now());
        Alarm alarm2 = new Alarm(null,user1,"기다리지 마시오 아저시",false, LocalDateTime.now());
        user1.getAlarms().add(alarm1);
        user1.getAlarms().add(alarm2);
        userRepository.save(user1);
    }
}
