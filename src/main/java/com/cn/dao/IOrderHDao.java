package com.cn.dao;

import com.cn.model.Order;
import com.cn.model.OrderH;
import com.cn.vo.OrderEx;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IOrderHDao {


    public List<OrderH> pageList(String OrderId);
    public void insert(OrderH orderh);



}