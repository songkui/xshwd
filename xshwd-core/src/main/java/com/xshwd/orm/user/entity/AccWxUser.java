package com.xshwd.orm.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xshwd.orm.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 微信用户信息
 * @author SK
 * @since 2019-03-02
 */

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

@ApiModel("微信用户信息")
@TableName("usr_wx_user")
public class AccWxUser extends BaseEntity<AccWxUser> {

    private static final long serialVersionUID = 114520457105520L;

    /**
     * 微信openID
     */
    @TableField("open_id")
    private String openId;
    /**
     * 微信UnionID
     */
    @TableField("union_id")
    private String unionId;
    /**
     * 微信昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 微信头像
     */
    @TableField("head_image")
    private String headImage;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 手机
     */
    @TableField("tel")
    private String tel;
    /**
     * 店铺idx
     */
    @TableField("shop_idx")
    private String shopIdx;
    /**
     * 性别(0 女 1男)
     */
    @TableField("sex")
    private Integer sex;


}
