package com.cn.dao.impl;

import com.cn.dao.IOrderDao;
import com.cn.dao.IOrderHDao;
import com.cn.model.Order;
import com.cn.model.OrderH;
import com.cn.vo.OrderEx;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("orderH" +
        "Dao")
public class OrderHDaoImpl extends BaseDaoImpl implements IOrderHDao {

    @Override
    public List<OrderH> pageList(String orderId) {
        return (List< OrderH>)list("com.cn.dao.OrderHMapper.selectPageByEntity",orderId);

    }


    @Override
    public void insert(OrderH  orderH) {
        addObject("com.cn.dao.OrderHMapper.insert",  orderH);
    }


}
