package com.xshwd.order.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/item/test")
public class ItemTestController {


    @GetMapping("/{test}")
    public String getTest(@PathVariable("test") String test){
        return test;
    }

}
