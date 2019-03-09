package com.xshwd.framework.web;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: SK ON  2018/11/2 16:06
 * @Description:
 */
public enum ResponseCode {

    /**
     * 公共
     */
    Success(ResponseCodeType.None, 200, "成功"),
    SystemException(ResponseCodeType.None, 500, "系统异常"),
    Forbidden(ResponseCodeType.None,403,"错误的请求参数"),

    /**
     * 全局返回错误
     * (这种情况出现在请求未进入业务层，在框架层出现的异常错误)
     */
    DataNotFound(ResponseCodeType.Other, 1, "指定数据不存在", "指定%s数据(%s)不存在"),
    IllegalArgument(ResponseCodeType.Other, 2, "参数错误", "传入的参数(%s)不正确"),
    BusinessException(ResponseCodeType.Other, 3, ""),

    UserLoginFailure(ResponseCodeType.User, 1, "用户登陆失效，请重新登陆"),
    UserBDNotExits(ResponseCodeType.User, 2, "指定BD用户不存在"),
    UserBDExceptionExits(ResponseCodeType.User, 3, "BD邀请码出现异常请速速联系后台管理员"),
    UserBDBind(ResponseCodeType.User, 4, "该店铺已绑定BD不能重复绑定"),

    ShopNotExist(ResponseCodeType.Shop, 1, "店铺不存在"),
    ShopMaterialData(ResponseCodeType.Shop, 2, "店铺资质数据不全"),
    ShopInfoNotFull(ResponseCodeType.Shop, 3, "店铺信息不完全"),
    ShopClose(ResponseCodeType.Shop, 4, "店铺已打烊，无法下单"),


    ItemNotOffline(ResponseCodeType.Merchandise, 1, "请选择企业自营商品"),
    CouponInvalid(ResponseCodeType.Merchandise, 2, "无法使用该优惠劵"),

    ItemBargainExist(ResponseCodeType.Activity, 1, "商品存在，请重新选择砍价商品"),
    ActivityDateError(ResponseCodeType.Activity, 2, "活动时间错误。奇怪重新选择开始时间"),
    BargainDetailsExist(ResponseCodeType.Activity, 3, "谢谢您,您已经对该商品砍过价啦"),
    ActivityNotExist(ResponseCodeType.Activity, 4, "砍价活动不存在"),
    BargainMinimum(ResponseCodeType.Activity, 9, "我已砍到最低啦,谢谢您.亲"),
    ActivityEnd(ResponseCodeType.Activity, 5, "砍价活动结束"),
    ActivityNotStarted(ResponseCodeType.Activity, 6, "砍价活动还没开始"),
    BargainExist(ResponseCodeType.Activity, 7, "该商品您已生成砍价信息"),
    BargainToBargain(ResponseCodeType.Activity, 8, "您不能对自己发起的砍价商品进行砍价"),
    ActivityNotStart(ResponseCodeType.Activity, 10, "活动还未开始"),
    ActivityStop(ResponseCodeType.Activity, 11, "活动已经停止使用")
    ;


    private final static Map<Integer, ResponseCode> codeMap = Arrays.stream(ResponseCode.values()).collect(Collectors.toMap(ResponseCode::code, code -> code));

    private final int code;
    private  String desc;
    private final String template;
    private final ResponseCodeType type;


    ResponseCode(ResponseCodeType type, int code, String desc) {
        this.code = code;
        this.desc = desc;
        this.type = type;
        this.template = "";
    }

    ResponseCode(ResponseCodeType type, int code, String desc, String template) {
        this.code = code;
        this.desc = desc;
        this.type = type;
        this.template = template;
    }

    public int code() {
        return this.type.code() * 100 + this.code;
    }

    public String desc() {
        return this.desc;
    }

    public String template() {
        return this.template;
    }

    /**
     * 可自定义返回 desc
     * @param desc
     * @return
     */
    public ResponseCode setDesc(String desc){
        this.desc = desc;
        return  this;
    }

    /**
     * @param code 代码
     * @return 转换出来的状态码
     */
    public static ResponseCode parse(Integer code) {
        return codeMap.get(code);
    }

}
