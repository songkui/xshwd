package com.xshwd.framework.web;

/**
 *
 */
public enum ResponseCodeType {
    None(0, "无"),
    System(50, "系统"),
    Other(55, "其他"),
    User(61, "用户"),
    Shop(65, "店铺"),
    Merchandise(70, "商品"),
    Order(80, "订单"),
    Activity(91, "活动"),
    Earnings(92, "收益"),
    ;
    
    private final int code;
    private final String desc;

    ResponseCodeType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}
