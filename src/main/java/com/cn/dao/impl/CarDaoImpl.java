package com.cn.dao.impl;

import com.cn.dao.ICarDao;
import com.cn.dao.impl.BaseDaoImpl;
import com.cn.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("carDao")
public class CarDaoImpl extends BaseDaoImpl implements ICarDao {

    @Override
    public List<Car> pageList( Car  car) {
        return (List< Car>)list("com.cn.dao.CarMapper.selectPageByEntity",  car);

    }

    @Override
    public List<Car> allValidList() {
        return (List< Car>)list("com.cn.dao.CarMapper.selectAllValid",  "");
    }

    @Override
    public void insert(Car  car) {
        addObject("com.cn.dao.CarMapper.insert",  car);
    }

    @Override
    public void update(Car  car) {
        updateObject("com.cn.dao.CarMapper.updateByPrimaryKey",  car);
    }

    @Override
    public  Car find(Car  car) {
        return ( Car)findObject("com.cn.dao.CarMapper.selectOneByEntity",  car);
    }

    @Override
    public void delete(String carId) {
        deleteObject("com.cn.dao.CarMapper.deleteById", carId);
    }
}
