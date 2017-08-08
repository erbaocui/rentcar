package com.cn.service;

import com.cn.model.Driver;

import java.util.List;

public interface IDriverService {
    //用户
    public List<Driver> getDriverPageByEntity(Driver driver);
    public List<Driver> getAllValidDriver();
    public Driver getDriverByEntity(Driver driver);
    public void addDriver(Driver driver);
    public void modifyDriver(Driver driver);
    public void deleteDriver(Driver driver);

}
