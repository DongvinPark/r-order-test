package com.example.rordertest.util;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisListUtil {

    public static List<Long> getLongListFromCacheServer(RedisTemplate<String, String> template, String key) {
        Long size = template.opsForList().size(key);
        if(size != null){
            List<String> listFromRedis = template.opsForList().range(key, 0, size);
            assert listFromRedis != null;
            return listFromRedis.stream().map(Long::valueOf).collect(Collectors.toList());
        } else {
            return null;
        }
    }

}
