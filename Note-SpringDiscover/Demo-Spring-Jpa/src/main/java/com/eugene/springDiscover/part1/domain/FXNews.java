package com.eugene.springDiscover.part1.domain;

import java.util.Date;

/**
 * @Classname FXNews
 * @Description 新闻实体类
 * @Date 2021/1/6 23:16
 * @Created by cairuojin
 */
public class FXNews {

    //新闻内容
    private String message;

    //新闻时间
    private Date createDate;

    //新闻来源Code
    private String remoteCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemoteCode() {
        return remoteCode;
    }

    public void setRemoteCode(String remoteCode) {
        this.remoteCode = remoteCode;
    }
}
