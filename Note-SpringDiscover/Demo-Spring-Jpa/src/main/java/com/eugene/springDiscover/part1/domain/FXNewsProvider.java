package com.eugene.springDiscover.part1.domain;

import java.util.List;

/**
 * @Classname FXNewsProvider
 * @Description 新闻获取流程类
 * @Date 2021/1/6 23:12
 * @Created by cairuojin
 */
public class FXNewsProvider {

    private IFXNewsListener ifxNewsListener;

    private IFXNewsPersister ifxNewsPersister;

    public void getAndPersistNews() {

        List<FXNews> newFromRemote = null;
        try {
            newFromRemote = ifxNewsListener.getNewFromRemote();
        } catch (Exception e) {
            System.out.println("获取新闻失败");
            e.printStackTrace();
        }

        try {
            ifxNewsPersister.saveNew(newFromRemote);
        } catch (Exception e) {
            System.out.println("保存新闻失败");
            e.printStackTrace();
        }
    }

    //构造方法
    public FXNewsProvider(IFXNewsListener ifxNewsListener, IFXNewsPersister ifxNewsPersister) {
        this.ifxNewsListener = ifxNewsListener;
        this.ifxNewsPersister = ifxNewsPersister;
    }

    public FXNewsProvider() {
    }

    //get / set方法
    public IFXNewsListener getIfxNewsListener() {
        return ifxNewsListener;
    }

    public void setIfxNewsListener(IFXNewsListener ifxNewsListener) {
        this.ifxNewsListener = ifxNewsListener;
    }

    public IFXNewsPersister getIfxNewsPersister() {
        return ifxNewsPersister;
    }

    public void setIfxNewsPersister(IFXNewsPersister ifxNewsPersister) {
        this.ifxNewsPersister = ifxNewsPersister;
    }
}
