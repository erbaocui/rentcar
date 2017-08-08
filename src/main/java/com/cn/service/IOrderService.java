package com.cn.service;

import com.cn.model.Order;
import com.cn.model.OrderH;
import com.cn.model.User;
import com.cn.vo.OrderEx;

import java.util.List;

public interface IOrderService {
    //订单
    public List<OrderEx> getOrderPageByEntity(OrderEx order);
    public List<OrderH> getOrderHPageByEntity(String orderId);
    public Order getOrderByEntity(Order order);
    public void addOrder(OrderEx order,User user);
    public void modifyOrder(OrderEx order,User user)throws Exception;
    public void deleteOrder(Order order);

}
