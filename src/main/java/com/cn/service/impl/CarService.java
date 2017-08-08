package com.cn.service.impl;

import com.cn.dao.ICarDao;
import com.cn.dao.ICarTypeDao;
import com.cn.model.Car;
import com.cn.model.CarType;
import com.cn.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("carService")
public class CarService implements ICarService {


    @Autowired
    private ICarDao carDao;
    @Autowired
    private ICarTypeDao carTypeDao;



    @Override
    public List<Car> getCarPageByEntity(Car car){
        return carDao.pageList(car);
    }

    @Override
    public List<Car> getAllValidCar() {
        return carDao.allValidList();
    }

    @Override
    public Car getCarByEntity(Car car) {
        return carDao.find(car);
    }
    @Override
    public void addCar(Car car) {
        carDao.insert(car);
    }

    @Override
    public void modifyCar(Car car) {
        carDao.update(car);
    }

    @Override
    public void deleteCar(Car car) {
        carDao.delete(car.getId());
    }

    @Override
    public List<CarType> getAllCarType() {
        return  carTypeDao.allList();
    }

    @Override
    public CarType getCarTypeByEntity(CarType CarType) {
        return carTypeDao.find(CarType);
    }

    public ICarDao getCarDao() {
        return carDao;
    }

    public void setCarDao(ICarDao carDao) {
        this.carDao = carDao;
    }

    public ICarTypeDao getCarTypeDao() {
        return carTypeDao;
    }

    public void setCarTypeDao(ICarTypeDao carTypeDao) {
        this.carTypeDao = carTypeDao;
    }
}
