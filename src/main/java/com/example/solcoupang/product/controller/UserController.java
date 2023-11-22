package com.example.solcoupang.product.controller;

import com.example.solcoupang.product.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v2/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/insert-test")
    public void userAlarmInsert(){
        userService.userAlarmInsert();
    }
}
