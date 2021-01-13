package com.gjsyoung.controller;

import com.gjsyoung.domain.SpittleNoFindExpection;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * create by cairuojin on 2019/01/18
 */
@ControllerAdvice
public class handleSpittleException {

    @ExceptionHandler(SpittleNoFindExpection.class)
    public String handleDuplicateSpittle(){
        System.out.println("找不到的异常，管理员快来");
        return "error";
    }
}
