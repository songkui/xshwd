package com.xshwd.user.config.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Auther: SK
 * @Date: 2019/3/16 11:12
 * @Description: redis 设置配置文件 notify-keyspace-events Ex
 */


@Configuration
public class RedisConfiguration {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }
//    @Bean
//    public KeyExpiredListener keyExpiredListener() {
//        return new KeyExpiredListener(this.redisMessageListenerContainer());
//    }



}
