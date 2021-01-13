package com.eugene.springDiscover.part1.domain;

import java.util.List;

/**
 * @Classname IFXNewsListener
 * @Description 新闻获取接口类
 * @Date 2021/1/6 23:13
 * @Created by cairuojin
 */
public interface IFXNewsListener {

    //实现该接口以获取新闻
    List<FXNews> getNewFromRemote();

}
