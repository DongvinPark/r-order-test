package com.example.rordertest.service;

import com.example.rordertest.persist.TestEntity;
import com.example.rordertest.persist.TestRepository;
import com.example.rordertest.redis.OrderCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    private final OrderCacheRepository orderCacheRepository;

    public boolean redisPushTest(String orderNumber){
        return orderCacheRepository.pushOrder(orderNumber);
    }

    public void redisGetListAndPopTestAndDBTest(){
        System.out.println("주문 큐 리스트 출력 : " + orderCacheRepository.getOrderList());
        System.out.println("주문 뽑아내기 : " + orderCacheRepository.popOrder());

        testRepository.save(
            TestEntity.builder()
                .build()
        );
    }

}
