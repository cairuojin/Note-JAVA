package com.eugene.springDiscover.part1.domain.DowJones;

import com.eugene.springDiscover.part1.domain.FXNews;
import com.eugene.springDiscover.part1.domain.IFXNewsListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname DowJonesNewsListener
 * @Description DowJones新闻获取类
 * @Date 2021/1/6 23:13
 * @Created by cairuojin
 */
public class DowJonesNewsListener implements IFXNewsListener{

    @Override
    public List<FXNews> getNewFromRemote() {
        System.out.println(new Date() + " DowJones新闻获取");
        List<FXNews> news = new ArrayList<>();
        return news;
    }

}
