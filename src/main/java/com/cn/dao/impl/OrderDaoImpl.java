package com.cn.dao.impl;

import com.cn.dao.IOrderDao;
import com.cn.model.Order;
import com.cn.vo.OrderEx;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl implements IOrderDao {

    @Override
    public List<OrderEx> pageList(OrderEx order) {
        return (List< OrderEx>)list("com.cn.dao.OrderMapper.selectPageByEntity",  order);

    }


    @Override
    public void insert(Order  order) {
        addObject("com.cn.dao.OrderMapper.insert",  order);
    }

    @Override
    public void update(Order  order) {
        updateObject("com.cn.dao.OrderMapper.updateByPrimaryKey",  order);
    }

    @Override
    public  Order find(Order  order) {
        return ( Order)findObject("com.cn.dao.OrderMapper.selectOneByEntity",  order);
    }

    @Override
    public void delete(String orderId) {
        deleteObject("com.cn.dao.OrderMapper.deleteById", orderId);
    }
}
