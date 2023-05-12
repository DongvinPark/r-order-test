package com.example.rordertest.service;

import com.example.rordertest.redis.OrderCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final OrderCacheRepository orderCacheRepository;

    public boolean redisTest(Long orderNumber){
        return orderCacheRepository.pushOrder(orderNumber);
    }

}
