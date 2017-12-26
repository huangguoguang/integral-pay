package com.acl.pay.enums;

import com.acl.pay.exception.CustomExceptionCode;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 14:39
 */
public enum PaymentExceptionEnum implements CustomExceptionCode{
    PARAM_IS_NULL(1001, 500),//参数缺失
    USERINFO_IS_ERROR(1002, 500)//用户信息错误
    ;

    private Integer code;
    private Integer httpCode;

    PaymentExceptionEnum(Integer code, Integer httpCode) {
        this.code = code;
        this.httpCode = httpCode;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getHttpCode() {
        return httpCode;
    }

    @Override
    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }
}
