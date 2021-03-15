package com.sour.mall.product.exception;

import com.sour.mall.common.exception.BizCodeEnume;
import com.sour.mall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  集中处理controller的异常
 *
 * @author xgl
 * @date 2021/3/13 14:25
 **/
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.sour.mall.product.controller")  合体到下面@RestControllerAdvice
@RestControllerAdvice(basePackages = "com.sour.mall.product.controller")
public class MallControllerAdvice {

    /**
     *  校验不通过 处理
     *
     * @author xgl
     * @date 2021/3/13 14:36
     **/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidExcetption(MethodArgumentNotValidException e ) {
        log.error(BizCodeEnume.VAILD_EXCEPTION.getMsg() + "{}, 异常类型{}", e.getMessage(), e.getClass());
        BindingResult result = e.getBindingResult();
        return R.notValid(result);
    }

    /**
     *  其他异常 处理
     *
     * @author xgl
     * @date 2021/3/13 14点42分
     **/
    @ExceptionHandler(value = Throwable.class)
    public R handleExcetption( Throwable e ) {
        log.error(BizCodeEnume.UNKNOWN_EXCEPTION.getMsg() + "{}, 异常类型{}", e.getMessage(), e.getClass());
        return R.error(BizCodeEnume.UNKNOWN_EXCEPTION.getCode(), BizCodeEnume.UNKNOWN_EXCEPTION.getMsg());
    }
}
