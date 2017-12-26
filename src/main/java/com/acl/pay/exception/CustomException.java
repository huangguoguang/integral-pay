package com.acl.pay.exception;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 14:31
 */
public class CustomException extends Exception {
    private static final long serialVersionUID = 1L;

    private CustomExceptionCode code;

    public CustomException() {
    }

    public CustomException(CustomExceptionCode code) {
        this.code = code;
    }

    public CustomException(String message, CustomExceptionCode code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause, CustomExceptionCode code) {
        super(message, cause);
        this.code = code;
    }

    public CustomException(Throwable cause, CustomExceptionCode code) {
        super(cause);
        this.code = code;
    }

    public CustomExceptionCode getCode() {
        return code;
    }

    public void setCustomExceptionCode(CustomExceptionCode code) {
        this.code = code;
    }
}
