package com.cn.dao;

import com.cn.model.Car;
import com.cn.model.Driver;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IDriverDao {


    public List<Driver> pageList(Driver driver);
    public List<Driver> allValidList();
    public void insert(Driver driver);
    public void update(Driver driver);
    public Driver find(Driver driver);
    public void delete(String driverId);


}