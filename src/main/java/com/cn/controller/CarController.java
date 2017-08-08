package com.cn.controller;

import com.cn.model.Car;
import com.cn.model.CarType;
import com.cn.service.ICarService;
import com.cn.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/car")
@Scope("prototype")
public class CarController extends BaseController{

    Logger logger= Logger.getLogger(CarController.class.getName());

    @Autowired
    private ICarService carService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     *车辆管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/car")
    public String person() throws Exception{
        return "redirect:/page/car/carList.jsp";
    }
   
    /**
     *车辆列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String plateNumber,String status)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Car car=new Car();
        if(StringUtil.isNotEmpty( plateNumber)) {
            car.setPlateNumber(plateNumber);
        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
               car.setStatus(status);
            }
        }


        List<Car> list=carService.getCarPageByEntity(car);
        PageInfo<Car>p=new PageInfo<Car>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     *车辆查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String plateNumber)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Car car=new Car();
        car.setPlateNumber(plateNumber);
        Car u=carService.getCarByEntity(car);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }
    /**
     *车辆删除
     * @param
     *
     * @return map json
     */

    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map delete(String id)throws Exception
    {
        Map result=new HashMap();

        Car car=new Car();
        car.setId(id);
        carService.deleteCar(car);

        result.put("result","success");
        return result;
    }



    /**
     *车辆添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String plateNumber,String typeId,String remark)throws Exception {
        Map result = new HashMap();
        Car car = new Car();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        car.setId(uuid);
        car.setPlateNumber(plateNumber);
        CarType carType=new CarType();
        carType.setId(typeId);
        carType=carService.getCarTypeByEntity(carType);
        car.setType(carType.getType());
        car.setTypeId(carType.getId());
        if (StringUtil.isNotEmpty(remark)){
            car.setRemark(remark);
        }
        car.setStatus("0");
        carService.addCar(car);
        result.put("result","success");
         return result;
    }

    /**
     *车辆编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String remark,String typeId,String status)throws Exception
    {
        Map result=new HashMap();
        Car car=new Car();
        car.setId(id);
        CarType carType=new CarType();
        carType.setId(typeId);
        carType=carService.getCarTypeByEntity(carType);
        car.setType(carType.getType());
        car.setTypeId(carType.getId());
        if (StringUtil.isNotEmpty(remark)) {
            car.setRemark(remark);
        }
        car.setStatus(status);

        carService.modifyCar(car);
        result.put("result","success");
        return result;
    }

    /**
     *车辆类型列表
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/typeList")
    public @ResponseBody
    List<CarType> typeList()throws Exception
    {
        List<CarType> list=carService.getAllCarType();
        return list;
    }

    /**
     *有效车辆列表
     * @param
     *
     * @return listCar json
     */
    @RequestMapping(value = "/validList")
    public @ResponseBody
    List<Car> validList()throws Exception
    {
        List<Car> list=carService.getAllValidCar();
        return list;
    }

    public ICarService getCarService() {
        return carService;
    }

    public void setCarService(ICarService carService) {
        this.carService = carService;
    }
}
