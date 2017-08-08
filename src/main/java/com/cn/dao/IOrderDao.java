package com.cn.dao;

import com.cn.model.Order;
import com.cn.vo.OrderEx;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IOrderDao {


    public List<OrderEx> pageList(OrderEx order);
    public void insert(Order order);
    public void update(Order order);
    public Order find(Order order);
    public void delete(String orderId);


}