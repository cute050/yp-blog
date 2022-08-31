package org.java.yp.exception;

import org.java.yp.enmu.AppHttpCodeEnum;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/29 10:13
 */

public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
