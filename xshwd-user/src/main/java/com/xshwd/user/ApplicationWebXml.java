/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-15 下午6:42
 *
 *
 */

package com.xshwd.user;

import com.xshwd.user.config.DefaultProfileUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This is a helper Java class that provides an alternative to creating a web.xml.
 * This will be invoked only when the application is deployed to a servlet container like Tomcat, JBoss etc.
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        /**
         * set a default to use when no profile is configured.
         */
        DefaultProfileUtil.addDefaultProfile(application.application());
        return application.sources(Application.class);
    }
}
