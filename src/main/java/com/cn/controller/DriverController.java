package com.cn.controller;

import com.cn.model.Driver;
import com.cn.service.IDriverService;
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
@RequestMapping("/driver")
@Scope("prototype")
public class DriverController extends BaseController{

    Logger logger= Logger.getLogger(DriverController.class.getName());

    @Autowired
    private IDriverService driverService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     *司机管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/driver")
    public String person() throws Exception{
        return "redirect:/page/driver/driverList.jsp";
    }
   
    /**
     *司机列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String name,String mobile,String status)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Driver driver=new Driver();
        if(StringUtil.isNotEmpty(name)) {
            driver.setName(name);
        }
        if(StringUtil.isNotEmpty(mobile)) {
            driver.setMobile(mobile);
        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                driver.setStatus(status);
            }
        }

        List<Driver> list=driverService.getDriverPageByEntity(driver);
        PageInfo<Driver>p=new PageInfo<Driver>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     *司机查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String mobile)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Driver driver=new Driver();
        driver.setMobile(mobile);
        Driver u=driverService.getDriverByEntity(driver);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }
    /**
     *司机删除
     * @param
     *
     * @return map json
     */

    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map delete(String id)throws Exception
    {
        Map result=new HashMap();

        Driver driver=new Driver();
        driver.setId(id);
        driverService.deleteDriver(driver);

        result.put("result","success");
        return result;
    }



    /**
     *司机添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String name,String mobile,String remark)throws Exception {
        Map result = new HashMap();
        Driver driver = new Driver();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        driver.setId(uuid);
        driver.setName(name);
        driver.setMobile(mobile);
        if (StringUtil.isNotEmpty(remark)){
            driver.setRemark(remark);
        }
        driver.setStatus("0");
        driverService.addDriver(driver);
        result.put("result","success");
         return result;
    }

    /**
     *司机编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String name,String remark,String status)throws Exception
    {
        Map result=new HashMap();
        Driver driver=new Driver();
        driver.setId(id);
        driver.setName(name);
        if (StringUtil.isNotEmpty(remark)) {
            driver.setRemark(remark);
        }
        driver.setStatus(status);

        driverService.modifyDriver(driver);
        result.put("result","success");
        return result;
    }
    /**
     *有效司机列表
     * @param
     *
     * @return listCar json
     */
    @RequestMapping(value = "/validList")
    public @ResponseBody
    List<Driver> validList()throws Exception
    {
        List<Driver> list=driverService.getAllValidDriver();
        return list;
    }

    public IDriverService getDriverService() {
        return driverService;
    }

    public void setDriverService(IDriverService driverService) {
        this.driverService = driverService;
    }
}
