package org.java.yp.handler.exception;

import lombok.extern.slf4j.Slf4j;
import org.java.yp.domain.ResponseResult;
import org.java.yp.enmu.AppHttpCodeEnum;
import org.java.yp.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/29 11:46
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常! {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e){
        //打印异常信息
        log.error("出现了异常! {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
