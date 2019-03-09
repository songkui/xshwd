/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-15 下午6:22
 *
 *
 */

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
