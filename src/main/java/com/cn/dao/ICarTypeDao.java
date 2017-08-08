package com.cn.dao;

import com.cn.model.Car;
import com.cn.model.CarType;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICarTypeDao {

    public List<CarType> allList();
    public CarType find(CarType carType);

}