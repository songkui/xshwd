package com.xshwd.order.webouter.order;

import com.xshwd.framework.util.Constants;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/item/test")
public class ItemTestController {


    @GetMapping(path = "/{test}",  produces = Constants.MEDIA_JSON_TYPE)
    public String getTest(@RequestHeader String token,  @PathVariable("test") String test){
        return test;
    }

}
