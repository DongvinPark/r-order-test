package com.example.rordertest.redis;

import com.example.rordertest.util.CommonConstant;
import com.example.rordertest.util.RedisListUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderCacheRepository {

    private final RedisTemplate<String, String> template;
    private final String key = CommonConstant.ORDER_QUEUE_KEY;

    // 새로운 들어온 주문들의 주키를 저장한다. 이때, 주문에 실패하면 false를 리턴한다.
    public boolean pushOrder(Long orderNumber) {
        try {
            template.opsForList().rightPush(key, String.valueOf(orderNumber));
            return true;
        } catch (Exception e) {
            log.error("신규 주문 정보 주문 큐에 푸시 실패");
            return false;
        }
    }

    // 조리를 시작할 주문을 꺼낸다.
    public String popOrder(){
        try {
            return template.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("조리 대상 주문 정보 주문 큐에서 pop 실패");
            return null;
        }
    }

    // 레디스에 저장돼 있는 리스트를 반환한다.
    public List<Long> getOrderList(){
        try {
            return RedisListUtil.getLongListFromCacheServer(template, key);
        } catch (Exception e){
            log.error("레디스로부터 주문 큐 리스트 획득 실패");
            return null;
        }
    }

}