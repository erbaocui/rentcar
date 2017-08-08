package com.cn.service.impl;

import com.cn.dao.IOrderDao;

import com.cn.dao.IStatisticsDao;
import com.cn.model.Statistics;
import com.cn.service.IStatisticsService;
import com.cn.vo.OrderEx;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by home on 2017/7/7.
 */
@Service("statisticsService")
public class StatisticsService implements IStatisticsService {


    @Autowired
    private IStatisticsDao statisticsDao;

    @Override
    public List<Statistics> getPageList(OrderEx orderEx) {
       return statisticsDao.pageList(orderEx);
    }

    @Override
    public Statistics getSum(OrderEx orderEx) {
        return  statisticsDao.findSum(orderEx);
    }

    public IStatisticsDao getStatisticsDao() {
        return statisticsDao;
    }

    public void setStatisticsDao(IStatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }
}
