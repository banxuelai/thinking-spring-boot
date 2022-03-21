package org.thinking.in.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.in.cache.service.CacheService;

import javax.annotation.Resource;

/**
 * @author bxl
 * @date 2022/3/14
 */
@RestController
public class CacheController {

    @Resource
    private CacheService cacheService;

    @GetMapping("testCache")
    public String test(@RequestParam("id") String id) {
        return  cacheService.test(id);
    }
}
