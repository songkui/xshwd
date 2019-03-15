package com.xshwd.framework.util;

import org.springframework.http.MediaType;

/**
 * @Auther: SK
 * @Date: 2019/3/10 21:35
 * @Description: 常量信息
 */
public interface Constants {
    //接口数据类型
    String MEDIA_JSON_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;
    String TOKEN = "token";

    int TOKEN_LIFECYCLE = 120; //登录信息过期时间 最好使用秒；
}
