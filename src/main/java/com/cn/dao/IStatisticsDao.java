package com.cn.dao;

import com.cn.model.Statistics;
import com.cn.model.User;
import com.cn.vo.OrderEx;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IStatisticsDao {


    public List<Statistics> pageList(OrderEx orderEx);
    public Statistics findSum(OrderEx orderEx);

}