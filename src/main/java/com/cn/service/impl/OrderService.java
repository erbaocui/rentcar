package com.cn.service.impl;

import com.cn.dao.IOrderDao;
import com.cn.dao.IOrderHDao;
import com.cn.model.Order;
import com.cn.model.OrderH;
import com.cn.model.User;
import com.cn.service.IOrderService;
import com.cn.vo.OrderEx;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by home on 2017/7/7.
 */
@Service("orderService")
public class OrderService implements IOrderService {


    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderHDao orderHDao;



    @Override
    public List<OrderEx> getOrderPageByEntity(OrderEx order){
        return orderDao.pageList(order);
    }

    @Override
    public List<OrderH> getOrderHPageByEntity(String orderId) {
        return orderHDao.pageList(orderId);
    }

    @Override
    public Order getOrderByEntity(Order order) {
        return orderDao.find(order);
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void addOrder(OrderEx order,User user) {
        OrderH  orderH=new OrderH();
        BeanUtils.copyProperties(order, orderH);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        orderH.setId(uuid);
        orderH.setOrderId(order.getId());
        orderH.setOptUser(user);
        order.setLastOptUser(user);
        orderDao.insert(order);
        orderHDao.insert(orderH);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)

    public void modifyOrder(OrderEx order,User user) throws Exception{

        OrderH  orderH=new OrderH();
        BeanUtils.copyProperties(order, orderH);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        orderH.setId(uuid);
        orderH.setOrderId(order.getId());
        orderH.setOptUser(user);
        order.setLastOptUser(user);
        orderDao.update(order);
        orderHDao.insert(orderH);
    }


    @Override
    public void deleteOrder(Order order) {
        orderDao.delete(order.getId());
    }

    public IOrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public IOrderHDao getOrderHDao() {
        return orderHDao;
    }

    public void setOrderHDao(IOrderHDao orderHDao) {
        this.orderHDao = orderHDao;
    }
}
