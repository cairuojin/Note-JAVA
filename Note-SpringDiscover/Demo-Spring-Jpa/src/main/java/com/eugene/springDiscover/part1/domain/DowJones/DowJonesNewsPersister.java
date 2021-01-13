package com.eugene.springDiscover.part1.domain.DowJones;

import com.eugene.springDiscover.part1.domain.FXNews;
import com.eugene.springDiscover.part1.domain.IFXNewsPersister;

import java.util.Date;
import java.util.List;

/**
 * @Classname DowJonesNewsPersister
 * @Description 新闻接口存储类
 * @Date 2021/1/6 23:13
 * @Created by cairuojin
 */
public class DowJonesNewsPersister implements IFXNewsPersister {

    @Override
    public void saveNew(List<FXNews> news) {
        System.out.println(new Date() + " DowJones新闻保存");
    }

}
