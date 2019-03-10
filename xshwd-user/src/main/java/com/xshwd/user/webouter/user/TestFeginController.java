package com.xshwd.user.webouter.user;

import com.xshwd.framework.web.ApiOut;
import com.xshwd.user.service.ItemTestFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/user/test")
public class TestFeginController {

    @Autowired
    private ItemTestFeginService itemTestFeginService;

    @GetMapping("/{test}")
    public ApiOut<String> getByshopId(@PathVariable("test") String openId){

        return new ApiOut.Builder<String>().data(itemTestFeginService.getTest(openId)).build() ;

    }

}
