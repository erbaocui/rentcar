package com.cn.service;

import com.cn.model.Car;
import com.cn.model.CarType;

import java.util.List;

public interface ICarService {
    public List<Car> getCarPageByEntity(Car car);
    public List<Car> getAllValidCar();
    public Car getCarByEntity(Car car);

    public void addCar(Car car);
    public void modifyCar(Car car);
    public void deleteCar(Car car);

    public List<CarType> getAllCarType();
    public CarType getCarTypeByEntity(CarType CarType);

}
