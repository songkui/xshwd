package com.xshwd.order.webouter.order;

import com.xshwd.framework.util.Constants;
import com.xshwd.framework.web.ApiOut;
import com.xshwd.order.service.FeginTestService;
import com.xshwd.orm.user.entity.AccWxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/item/spu")
public class ItemController {

    @Autowired
    private FeginTestService feginTestService;



    @GetMapping(path = "/{openId}" , produces = Constants.MEDIA_JSON_TYPE)
//    @PutMapping(path = "/purchase/{sid}/{code}", produces = Constants.MEDIA_JSON_TYPE)
    public ApiOut<AccWxUser> getByshopId(@RequestHeader String token, @PathVariable("openId") String openId){
        System.out.println("~~~~Token:"+token);
        return new ApiOut.Builder<AccWxUser>().data(feginTestService.getUserByOpenId(token, openId)).build();
    }


}
