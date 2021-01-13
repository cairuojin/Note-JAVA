package com.gjsyoung.service.Impl;

import com.gjsyoung.domain.Category;
import com.gjsyoung.mapper.CategoryMapper;
import com.gjsyoung.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by cairuojin on 2019/01/08
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    CategoryMapper categoryMapper;

    public Category selectAll() {
        return categoryMapper.selectByPrimaryKey(1);
    }
}
