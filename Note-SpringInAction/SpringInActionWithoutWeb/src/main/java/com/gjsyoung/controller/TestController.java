package com.gjsyoung.controller;

import com.gjsyoung.domain.Spittle;
import com.gjsyoung.domain.SpittleNoFindExpection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * create by cairuojin on 2019/01/08
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/totestPage" , method = RequestMethod.GET)
    public String testMethod(){
        Spittle spittle = null;
        //模拟在数据库中找寻不到对象
        if(spittle == null){
            throw new SpittleNoFindExpection();
        }
        System.out.println("123");
        return "test123";
    }

    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public String upload(MultipartFile multipartFile) throws IOException {
        multipartFile.transferTo(new File("F:/test/" + multipartFile.getOriginalFilename()));
        return "test123";
    }
}
