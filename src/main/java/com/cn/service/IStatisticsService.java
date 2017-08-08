package com.cn.service;

import com.cn.model.Statistics;
import com.cn.model.User;
import com.cn.vo.OrderEx;

import java.util.List;

public interface IStatisticsService {
    //统计

    public List<Statistics>  getPageList(OrderEx orderEx);
    public Statistics  getSum(OrderEx orderEx);

}
