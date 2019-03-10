
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
