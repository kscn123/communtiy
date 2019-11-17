package com.kscn.springbootcommunity.advice;

import com.kscn.springbootcommunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
//默认通用的异常处理
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model  model){
        if (e instanceof CustomizeException){  //这里是要使用通用的异常处理方式
            model.addAttribute("message",e.getMessage());

        }else {
            model.addAttribute("message","服务冒烟了，要不你稍后再试试~!");
        }
        return  new ModelAndView("error");
    }

}
