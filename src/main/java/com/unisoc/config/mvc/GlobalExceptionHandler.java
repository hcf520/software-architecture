package com.unisoc.config.mvc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.unisoc.utils.ResultUtil;

/**
 * 全局异常处理
 * 作用域：所有注解了@RequestMapping的控制器的方法上，说白了就是Controller
 * @author Chuanfei.he
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
 
    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException ex) {
        log.error(resultFormat(1, ex));
        return resultFormat(1, ex);
    }
 
    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        System.err.println("NullPointerException:");
        log.error(resultFormat(2, ex));
        return resultFormat(2, ex);
    }
 
    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        log.error(resultFormat(3, ex));
        return resultFormat(3, ex);
    }
 
    //IO异常
    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException ex) {
        log.error(resultFormat(4, ex));
        return resultFormat(4, ex);
    }
 
    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error(resultFormat(5, ex));
        return resultFormat(5, ex);
    }
 
    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error(resultFormat(6, ex));
        return resultFormat(6, ex);
    }
 
    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        log.error(resultFormat(7, ex));
        System.out.println("400..requestNotReadable");
        return resultFormat(7, ex);
    }
 
    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        log.error(resultFormat(8, ex));
        System.out.println("400..TypeMismatchException");
        return resultFormat(8, ex);
    }
 
    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        log.error(resultFormat(9, ex));
        System.out.println("400..MissingServletRequest");
        return resultFormat(9, ex);
    }
 
    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        log.error(resultFormat(10, ex));
        return resultFormat(10, ex);
    }
 
    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        log.error(resultFormat(11, ex));
        System.out.println("406...");
        return resultFormat(11, ex);
    }
 
    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public String server500(RuntimeException ex) {
        log.error(resultFormat(12, ex));
        System.out.println("500...");
        return resultFormat(12, ex);
    }
 
    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError ex) {
        log.error(resultFormat(13, ex));
        return resultFormat(13, ex);
    }
 
    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public String arithmeticException(ArithmeticException ex) {
        log.error(resultFormat(13, ex));
        return resultFormat(13, ex);
    }
 
 
    //其他错误
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        log.error(resultFormat(14, ex));
        return resultFormat(14, ex);
    }
 
    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        log.error(JSON.toJSONString(ResultUtil.error(code, ex.getMessage())));
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JSON.toJSONString(ResultUtil.error(code, ex.getMessage()));
    }
 
}
