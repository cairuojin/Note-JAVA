package com.gjsyoung.controller;

import com.gjsyoung.domain.Category;
import com.gjsyoung.domain.User;
import com.gjsyoung.jdbcTemplate.JdbcUserRepository;
import com.gjsyoung.mapper.UserMapper;
import com.gjsyoung.service.TestService;
import com.gjsyoung.test.Boss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * create by cairuojin on 2019/01/08
 */
@Controller
public class testController {

//    @Autowired
//    UserMapper userMapper;

    @Autowired
    JdbcUserRepository jdbcUserRepository;

    @RequestMapping("/test")
    public String testMethod(){
        User user = new User();
        user.setUsername("aaa");
        user.setPassword("bbb");
        user.setId(1);
        jdbcUserRepository.update(user);
        return "test";
    }


}
