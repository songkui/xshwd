/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-15 下午6:42
 *
 *
 */

package com.xshwd.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class DateTimeFormatConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
}
