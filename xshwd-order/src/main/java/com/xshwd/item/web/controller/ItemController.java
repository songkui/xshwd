package com.xshwd.item.web.controller;

import com.xshwd.framework.web.ApiOut;
import com.xshwd.item.fegin.UserFeginService;
import com.xshwd.item.service.FeginTestService;
import com.xshwd.orm.user.entity.AccWxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/item/spu")
public class ItemController {

    @Autowired
    private FeginTestService feginTestService;



    @GetMapping("/{openId}")
    public ApiOut<AccWxUser> getByshopId(@PathVariable("openId") String openId){
        return new ApiOut.Builder<AccWxUser>().data(feginTestService.getUserByOpenId(openId)).build();
    }


}
