package com.cn.dao;

import com.cn.model.Car;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICarDao {


    public List<Car> pageList(Car car);
    public List<Car> allValidList();
    public void insert(Car car);
    public void update(Car car);
    public Car find(Car car);
    public void delete(String carId);


}