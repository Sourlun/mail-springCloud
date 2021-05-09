package com.sour.mall.common.exception;

/**
 * 错误码 枚举
 *
 * @author xgl
 * @date 2021/3/13 14:48
 **/
public enum BizCodeEnume {

    /**
     * 系统未知错误
     */
    UNKNOWN_EXCEPTION(10000, "系统未知错误"),

    /**
     * 参数格式校验失败
     */
    VAILD_EXCEPTION(10001, "参数格式校验失败"),

    /**
     * 商品上架异常
     */
    PRODUCT_UP__EXCEPTION(11000, "商品上架异常");

    /**
     *  错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
