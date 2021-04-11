package com.sour.mall.common.constant;

/**
 * 产品 常量
 *
 * @author xgl
 * @date 2021/4/3 16:02
 **/
public class ProductConstant {

    public enum AttrEnum {
        ATTR_TYPE_BASE(1, "基本属性"), ATTR_TYPE_SALE(0, "销售属性");

        /**
         *  code
         */
        private int code;

        /**
         *  msg
         */
        private String msg;

        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
