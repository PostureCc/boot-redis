package com.chan.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: Chan
 * @Date: 2020/6/22 15:47
 * @Description:
 */
@RestController
public class TestController {

    @Resource(name = "redisTemplateToDefault")
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplateToDevice")
    private RedisTemplate<String, Object> redisTemplateToDevice;

    @GetMapping("test1")
    public void test1() {
        System.out.println(redisTemplate.opsForValue().get("auth_user_info:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMCIsImlhdCI6MTU5Mjc5NDk2Niwic3ViIjoiYWxsIiwiaXNzIjoid2V0b29sIn0.vi5hi4iFyDgWfwAXxfjhMjz_tbBmxP8D1LTOYqxP_OA"));
        System.out.println(redisTemplateToDevice.opsForValue().get("auth_user_info:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTg1MDE3ODU0LCJzdWIiOiJhbGwiLCJpc3MiOiJ3ZXRvb2wifQ.R3Kj1p7pk5_smod5c0675XyDFPjbybQUIeCmRiXoQwE"));
    }

}
