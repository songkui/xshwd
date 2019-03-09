/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-15 下午6:22
 *
 *
 */

package com.xshwd.item.config.fegin;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xshwd.framework.exception.MiniBuykerException;
import feign.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;

@Configuration
public class FeginConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // public class IndexFallbackFactory implements
    // FallbackFactory<CusFeignService> {
    //
    // @Override
    // public CusFeignService create(Throwable arg0) {
    // // TODO Auto-generated method stub
    // return new C;
    // }
    //
    // }

    @Bean
    public BizExceptionFeignErrorDecoder ecBizExceptionFeignErrorDecoder() {

        return new BizExceptionFeignErrorDecoder();
    }

    public class BizExceptionFeignErrorDecoder implements feign.codec.ErrorDecoder {

        // private static final Logger logger =
        // LoggerFactory.getLogger(BizExceptionFeignErrorDecoder.class);

        @Override
        public Exception decode(String methodKey, Response response) {

            if (response.status() >= 400 && response.status() < 500) {

                if(response.body()==null){

                    return  new MiniBuykerException(response.status(), "未知错误");
                }
                StringBuffer buffer = new StringBuffer();
                try {
                    BufferedReader bu = new BufferedReader(response.body().asReader());


                    if(bu!=null) {
                        String line = "";
                        while ((line = bu.readLine()) != null) {
                            buffer.append(line);
                        }
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return new MiniBuykerException(response.status(), buffer.toString());

            }

            if (response.status() >500 || response.status() <400) {
                return new HystrixBadRequestException("");
            }

            return feign.FeignException.errorStatus(methodKey, response);
        }

    }

    // 重试机制

    @Bean

    public Retryer feginRetryer() {
        Retryer retryer = Retryer.NEVER_RETRY;
        return retryer;
    }

    //
    // // 超时机制
    @Bean
    public Request.Options feginOption() {
        Request.Options option = new Request.Options(14000, 14000);
        return option;
    }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {

                requestTemplate.header("Authorization", "Bearer " + SecurityUtils.getCurrentUserJWT());
            }
        };
    }
}
