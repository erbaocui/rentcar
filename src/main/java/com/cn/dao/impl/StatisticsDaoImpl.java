package com.cn.dao.impl;

import com.cn.dao.IStatisticsDao;
import com.cn.model.Statistics;
import com.cn.vo.OrderEx;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/29.
 */
@Repository("statisticsDao")
public class StatisticsDaoImpl extends BaseDaoImpl implements IStatisticsDao {
    @Override
    public List<Statistics> pageList(OrderEx orderEx) {
        return (List<Statistics>)list("com.cn.dao.StatisticsMapper.selectPageList", orderEx);
    }

    @Override
    public Statistics findSum(OrderEx orderEx) {
        return (Statistics)findObject("com.cn.dao.StatisticsMapper.selectSum", orderEx);
    }
}
