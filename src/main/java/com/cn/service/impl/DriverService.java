package com.cn.service.impl;

import com.cn.dao.IDriverDao;
import com.cn.model.Driver;
import com.cn.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("driverService")
public class DriverService implements IDriverService {


    @Autowired
    private IDriverDao driverDao;



    @Override
    public List<Driver> getDriverPageByEntity(Driver driver){
        return driverDao.pageList(driver);
    }

    @Override
    public List<Driver> getAllValidDriver() {
        return driverDao.allValidList();
    }

    @Override
    public Driver getDriverByEntity(Driver driver) {
        return driverDao.find(driver);
    }
    @Override
    public void addDriver(Driver driver) {
        driverDao.insert(driver);
    }

    @Override
    public void modifyDriver(Driver driver) {
        driverDao.update(driver);
    }

    @Override
    public void deleteDriver(Driver driver) {
        driverDao.delete(driver.getId());
    }

    public IDriverDao getDriverDao() {
        return driverDao;
    }

    public void setDriverDao(IDriverDao driverDao) {
        this.driverDao = driverDao;
    }

}
