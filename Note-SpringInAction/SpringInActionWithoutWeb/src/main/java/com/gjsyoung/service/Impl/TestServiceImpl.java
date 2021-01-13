package com.gjsyoung.service.Impl;

import com.gjsyoung.mapper.CategoryMapper;
import com.gjsyoung.service.TestService;
import com.gjsyoung.test.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by cairuojin on 2019/01/08
 */
@Service
public class TestServiceImpl implements TestService {

    public void selectAll() {
        System.out.println("123");
    }
}
