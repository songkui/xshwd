package com.xshwd.order.fegin;

import com.xshwd.framework.util.Constants;
import com.xshwd.framework.web.ApiOut;
import com.xshwd.order.config.fegin.FeginConfiguration;
import com.xshwd.orm.user.entity.AccWxUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user", configuration = FeginConfiguration.class)
public interface UserFeginService {


    @GetMapping(path = "api/user/wxuser/{openId}", produces = Constants.MEDIA_JSON_TYPE)
    ApiOut<AccWxUser> getByshopId( @RequestHeader(Constants.TOKEN) String token, @PathVariable("openId") String openId);

}
