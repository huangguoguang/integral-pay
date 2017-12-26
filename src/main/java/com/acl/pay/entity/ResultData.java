package com.acl.pay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 14:02
 */
@Data
public class ResultData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status = 0 ; //访问成功，访问失败。

    private String message ;//回调信息

    private Integer code = 200;

    private Object data;

}
