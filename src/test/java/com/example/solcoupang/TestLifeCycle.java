package com.example.solcoupang;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class TestLifeCycle {
    
    @BeforeAll
    static void beforeAll(){
        log.info("--BeforAll 호출");
    }

    @AfterAll
    static void AfterAll(){
        log.info("--AfterAll 호출");
    }
    @BeforeEach
    void beforEach(){
        log.info("--BeforEach 호출");
    }

    @AfterEach
    void afterEach(){
        log.info("--afterEach 호출");
    }

    @Test
    void test(){
        log.info("--test 시작");
    }

    @Test
    @DisplayName("Test Case2!")
    void displayNameTest(){
        log.info("--displayNameTest 시작");
    }

    @Test
    @Disabled
    @DisplayName("Test Case3!")
    void disabledTest(){
        log.info("--DisabledTest 시작");
    }

}
