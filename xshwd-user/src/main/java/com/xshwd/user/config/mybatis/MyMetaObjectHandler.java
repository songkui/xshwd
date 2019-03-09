/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-16 下午10:46
 *
 *
 */

package com.xshwd.user.config.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {


        super.setFieldValByName("createTime",new Date(),metaObject);


        super.setFieldValByName("status",1,metaObject);


        super.setFieldValByName("version",1,metaObject);


    }



    @Override
    public void updateFill(MetaObject metaObject) {

        super.setFieldValByName("updateTime",new Date(),metaObject);




    }
}
