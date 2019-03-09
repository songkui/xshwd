/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-15 下午6:22
 *
 *
 */

package com.xshwd.user.service;

import com.xshwd.user.config.fegin.FeginConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order", configuration = FeginConfiguration.class)
public interface ItemTestFeginService {

    @GetMapping("api/item/test/{test}")
     String getTest(@PathVariable("test") String test);

}
