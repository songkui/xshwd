package com.xshwd.order.service;

import com.xshwd.order.fegin.UserFeginService;
import com.xshwd.orm.user.entity.AccWxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: SK ON  2018/10/29 14:58
 * @Description:
 */

@Service
public class FeginTestService {

    @Autowired
    private UserFeginService userFeginService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 通过openId查询
     * @param openId
     * @return
     */
    public AccWxUser getUserByOpenId(String token, String openId){
        try{
            System.out.println("----~~~~~~~~~~~~~~--------------");
            stringRedisTemplate.opsForValue().set("AASKAA","123");
            stringRedisTemplate.expire("AASKAA", 5, TimeUnit.MINUTES);
            System.out.println("-----~~~~~~~~~~-------------");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return userFeginService.getByshopId(token, openId).getData();
    }





}
