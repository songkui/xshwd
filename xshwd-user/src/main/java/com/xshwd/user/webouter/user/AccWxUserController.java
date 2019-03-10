package com.xshwd.user.webouter.user;

import com.xshwd.framework.util.Constants;
import com.xshwd.framework.web.ApiOut;
import com.xshwd.orm.user.entity.AccWxUser;
import com.xshwd.user.service.AccWxUserService;
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

}
