package com.example.rordertest.controller;

import com.example.rordertest.persist.TestEntity;
import com.example.rordertest.persist.TestRepository;
import com.example.rordertest.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestRestController {
    private final TestService testService;
    private final TestRepository testRepository;

    @PostMapping("/redis/test/{number}")
    public void redisTest(@PathVariable Long number){
        // 레디스에 키-밸류 1-2 등록해본다.
        testService.redisTest(number);
    }

    @PostMapping("/db/test")
    public void dbTest(){
        testRepository.save(
            TestEntity.builder()
                .build()
        );
    }

}
