/*
 *  THIS FILE IS PART OF C8software PROJECT
 * Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 * Mr.Yellow (www.c8software.com) 18-1-15 下午6:23
 *
 *
 */

package com.xshwd.orm;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseEntity<T> implements  Serializable{


    @TableId(type=IdType.ID_WORKER)
    @TableField("idx")
    public Long idx;


    @ApiModelProperty(readOnly = true)
    @TableLogic(delval = "0")
    @TableField( value =  "status",fill = FieldFill.INSERT)
    public Integer status;


    public Serializable pkVal() {
        return this.idx;
    }


    @ApiModelProperty("数据版本号，请原封不动动返回,更新数据不要传版本号")
    @TableField( value =  "version",fill = FieldFill.INSERT)
    @Version
    public Integer version;


    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    public Date createTime;


    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    public Date updateTime;


}
