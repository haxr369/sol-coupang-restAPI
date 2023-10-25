package com.example.solcoupang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @GetMapping({"/log"})
    public String getAll() {
        System.out.println("테스트");
        return "Success Test";
    }

    public TestController() {
    }
}