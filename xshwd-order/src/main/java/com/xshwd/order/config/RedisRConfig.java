//package com.xshwd.item.config;//package com.xshwd.item.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @Auther: SK
// * @Date: 2019/3/5 22:19
// * @Description:
// */
//@Configuration
//@EnableCaching//开启缓存
//class RedisRConfig extends CachingConfigurerSupport {
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
//        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        return cacheManager;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(factory);
//        return redisTemplate;
//    }
//}
//
//
//
