package com.chan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Auther: Chan
 * @Date: 2020/6/22 15:47
 * @Description:
 */

@Configuration
public class RedisConfigToDevice extends RedisConfig {

    @Value("${spring.redis.device.database}")
    private int dbIndex;

    @Value("${spring.redis.device.host}")
    private String host;

    @Value("${spring.redis.device.port}")
    private int port;

    @Value("${spring.redis.device.password}")
    private String password;

    @Value("${spring.redis.device.timeout}")
    private int timeout;

    /**
     * 配置redis连接工厂
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory realTimeRedisConnectionFactory() {
        return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "redisTemplateToDevice")
    public RedisTemplate<String, String> redisTemplateToDevice() {
        RedisTemplate<String, String> template = new RedisTemplate<String, String>();
        template.setConnectionFactory(realTimeRedisConnectionFactory());
        //key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误；
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        template.setKeySerializer(redisSerializer);
        template.setHashKeySerializer(redisSerializer);
        //设置序列化Value的实例化对象
        template.setValueSerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        template.afterPropertiesSet();
        return template;
    }
}

