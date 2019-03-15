package com.xshwd.user.webouter.user;

import com.alibaba.fastjson.JSON;
import com.xshwd.framework.util.Constants;
import com.xshwd.framework.web.ApiOut;
import com.xshwd.orm.user.entity.AccWxUser;
import com.xshwd.user.service.AccWxUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("api/user/wxuser")
public class AccWxUserController {

    @Autowired
    private AccWxUserService accWxUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(path = "/{openId}", produces = Constants.MEDIA_JSON_TYPE)
//    @PutMapping(path = "/purchase/{sid}/{code}", produces = Constants.MEDIA_JSON_TYPE)
    public ApiOut<AccWxUser> getByshopId(@RequestHeader String token, @PathVariable("openId") String openId){
        System.out.println("----============Token="+token);
        stringRedisTemplate.opsForValue().set("User","123");
        stringRedisTemplate.expire("User", 5, TimeUnit.MINUTES);
        System.out.println("-----==========-------------");
        return new ApiOut.Builder<AccWxUser>().data(accWxUserService.getUserByOpenId(openId)).build() ;
    }

    @PostMapping(produces = Constants.MEDIA_JSON_TYPE)
    public ApiOut<String> login( @RequestBody AccWxUser user){
       return new ApiOut.Builder<String>().data( accWxUserService.login(user) ).build();
    }

    @GetMapping(path = "/token", produces = Constants.MEDIA_JSON_TYPE)
    public ApiOut<AccWxUser> login( @RequestHeader String token){
        //获取 redis 中的值
        String userJson = stringRedisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userJson)){
            return new ApiOut.Builder<AccWxUser>().data( new AccWxUser() ).build();
        }
        try {
            AccWxUser user = JSON.parseObject(userJson, AccWxUser.class);
            if (null == user){
                return new ApiOut.Builder<AccWxUser>().data( new AccWxUser() ).build();
            }
            stringRedisTemplate.expire(token, Constants.TOKEN_LIFECYCLE, TimeUnit.MINUTES);
            return new ApiOut.Builder<AccWxUser>().data( user ).build();
        }catch (Exception e){
            return new ApiOut.Builder<AccWxUser>().data( new AccWxUser() ).build();
        }

    }

}
