package org.thinking.in.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author bxl
 * @date 2022/3/14
 */
@Service
@Slf4j
public class CacheService {

    @Cacheable(value = "test", key = "'testCache:'+ #id")
    public String test(String id) {
        log.info("test:{}", id);
        return id;
    }
}
