package com.cn.controller;

import com.cn.model.Supplier;
import com.cn.service.ISupplierService;
import com.cn.util.MD5Util;
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
@RequestMapping("/supplier")
@Scope("prototype")
public class SupplierController extends BaseController{

    Logger logger= Logger.getLogger(SupplierController.class.getName());

    @Autowired
    private ISupplierService supplierService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     *供应商管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/supplier")
    public String person() throws Exception{
        return "redirect:/page/supplier/supplierList.jsp";
    }
   
    /**
     *供应商列表查询
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
        Supplier supplier=new Supplier();
        if(StringUtil.isNotEmpty(name)) {
            supplier.setName(name);
        }
        if(StringUtil.isNotEmpty(mobile)) {
            supplier.setMobile(mobile);
        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                supplier.setStatus(status);
            }
        }

        List<Supplier> list=supplierService.getSupplierPageByEntity(supplier);
        PageInfo<Supplier>p=new PageInfo<Supplier>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     *供应商查询
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
        Supplier supplier=new Supplier();
        supplier.setMobile(mobile);
        Supplier u=supplierService.getSupplierByEntity(supplier);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }
    /**
     *供应商删除
     * @param
     *
     * @return map json
     */

    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map delete(String id)throws Exception
    {
        Map result=new HashMap();

        Supplier supplier=new Supplier();
        supplier.setId(id);
        supplierService.deleteSupplier(supplier);

        result.put("result","success");
        return result;
    }



    /**
     *供应商添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String name,String mobile,String contactName,String remark)throws Exception {
        Map result = new HashMap();
        Supplier supplier = new Supplier();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        supplier.setId(uuid);
        supplier.setName(name);
        supplier.setMobile(mobile);
        supplier.setContactName(contactName);
        if (StringUtil.isNotEmpty(remark)){
            supplier.setRemark(remark);
        }
        supplier.setStatus("0");
        supplierService.addSupplier(supplier);
        result.put("result","success");
         return result;
    }

    /**
     *供应商编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String name,String remark,String contactName,String status)throws Exception
    {
        Map result=new HashMap();
        Supplier supplier=new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setContactName(contactName);
        if (StringUtil.isNotEmpty(remark)) {
            supplier.setRemark(remark);
        }
        supplier.setStatus(status);

        supplierService.modifySupplier(supplier);
        result.put("result","success");
        return result;
    }
    /**
     *有效车辆列表
     * @param
     *
     * @return listCar json
     */
    @RequestMapping(value = "/validList")
    public @ResponseBody
    List<Supplier> validList()throws Exception
    {
        List<Supplier> list=supplierService.getAllValidSupplier();
        return list;
    }

    public ISupplierService getSupplierService() {
        return supplierService;
    }

    public void setSupplierService(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }
}
