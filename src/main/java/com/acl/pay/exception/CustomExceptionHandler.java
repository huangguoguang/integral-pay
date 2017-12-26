package com.acl.pay.exception;

import com.acl.pay.entity.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 15:04
 */
@ControllerAdvice
public class CustomExceptionHandler {
    public CustomExceptionHandler() {
    }

    @ExceptionHandler({CustomException.class})
    @ResponseBody
    public ResultData exceptionHandler(HttpServletResponse response, Exception ex) {
        ResultData result = new ResultData();
        if (ex instanceof CustomException) {
            ex.printStackTrace();
            CustomException exception = (CustomException) ex;
            response.setStatus(exception.getCode().getHttpCode());
            result.setCode(exception.getCode().getCode());
        }
        return result;
    }

}
