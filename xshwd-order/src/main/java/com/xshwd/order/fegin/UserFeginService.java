package com.xshwd.order.fegin;

import com.xshwd.framework.web.ApiOut;
import com.xshwd.order.config.fegin.FeginConfiguration;
import com.xshwd.orm.user.entity.AccWxUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user", configuration = FeginConfiguration.class)
public interface UserFeginService {

    @GetMapping("api/user/wxuser/{openId}")
     ApiOut<AccWxUser> getByshopId(@PathVariable("openId") String openId);

}
