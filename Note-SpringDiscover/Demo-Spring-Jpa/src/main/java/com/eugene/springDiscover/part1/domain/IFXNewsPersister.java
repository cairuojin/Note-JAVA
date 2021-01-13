package com.eugene.springDiscover.part1.domain;

import java.util.List;

/**
 * @Classname IFXNewsPersister
 * @Description 新闻接口存储类
 * @Date 2021/1/6 23:13
 * @Created by cairuojin
 */
public interface IFXNewsPersister {

    //实现该接口以存储新闻
    void saveNew(List<FXNews> news);

}
