package com.gjsyoung.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * create by cairuojin on 2019/01/18
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "找不到相应的Spittle")
public class SpittleNoFindExpection  extends RuntimeException{
}
