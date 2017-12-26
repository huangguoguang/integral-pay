package com.acl.pay.exception;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 14:38
 */
public interface CustomExceptionCode {
    Integer getCode();

    void setCode(Integer var1);

    Integer getHttpCode();

    void setHttpCode(Integer var1);
}
