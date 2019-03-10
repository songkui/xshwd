package com.xshwd.user.webouter.user;

import com.xshwd.framework.util.Constants;
import com.xshwd.framework.web.ApiOut;
import com.xshwd.user.service.ItemTestFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user/test")
public class TestFeginController {

    @Autowired
    private ItemTestFeginService itemTestFeginService;

    @GetMapping(path = "/{test}", produces = Constants.MEDIA_JSON_TYPE)
    public ApiOut<String> getByshopId(@RequestHeader String token, @PathVariable("test") String openId){

        return new ApiOut.Builder<String>().data(itemTestFeginService.getTest(token, openId)).build() ;

    }

}
