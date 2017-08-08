package com.cn.vo;

import com.cn.model.Order;
import com.cn.model.User;

import java.sql.Date;

/**
 * Created by home on 2017/7/27.
 */
public class OrderEx extends Order {
    private String beginTime;
    private String endTime;
    private String groupType;//0乘客供货商
    private User lastOptUser;

    public OrderEx() {
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public User getLastOptUser() {
        return lastOptUser;
    }

    public void setLastOptUser(User lastOptUser) {
        this.lastOptUser = lastOptUser;
    }
}
