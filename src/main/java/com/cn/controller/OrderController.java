package com.cn.controller;

import com.cn.constant.*;
import com.cn.dao.IAdminDao;
import com.cn.model.Customer;
import com.cn.model.Order;
import com.cn.model.OrderH;
import com.cn.model.User;
import com.cn.service.IAdminService;
import com.cn.service.ICustomerService;
import com.cn.service.IOrderService;
import com.cn.service.IUserService;
import com.cn.util.*;
import com.cn.view.ViewExcel;
import com.cn.vo.OrderEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/order")
@Scope("prototype")
public class OrderController extends BaseController{

    Logger logger= Logger.getLogger(OrderController.class.getName());

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAdminService  adminService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     *订单管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/order")
    public String person() throws Exception{
        return "redirect:/page/order/orderList.jsp";
    }
   
    /**
     *订单列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String beginTime,String endTime,String customerName,String supplierName,String orderStatus,HttpSession session)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        OrderEx order=new OrderEx();
        if(StringUtil.isNotEmpty(customerName)) {
            order.setCustomerName(customerName);
            session.setAttribute("customerName",customerName);
        }
        if(StringUtil.isNotEmpty(supplierName)) {
            order.setSupplierName(supplierName);
            session.setAttribute("supplierName",supplierName);
        }


        if(StringUtil.isNotEmpty(beginTime)) {
            order.setBeginTime(beginTime);
            session.setAttribute("beginTime",beginTime);
        }
        if(StringUtil.isNotEmpty(endTime)) {
            order.setEndTime(endTime);
            session.setAttribute("endTime",endTime);
        }
        if(StringUtil.isNotEmpty(orderStatus)){
         if(!orderStatus.equals("0")){
             order.setStatus(orderStatus);
         }
       }
        List<OrderEx> list=orderService.getOrderPageByEntity(order);
        PageInfo<OrderEx>p=new PageInfo<OrderEx>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }
    /**
     *订单列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/hlist")
    public @ResponseBody
    Map hlist(@RequestParam(value="page", required=false) String page,
             @RequestParam(value="rows", required=false) String rows,
             String orderId)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));

        List<OrderH> list=orderService.getOrderHPageByEntity(orderId);
        PageInfo<OrderH>p=new PageInfo<OrderH>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     *订单查询
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
        Order order=new Order();
       // order.setMobile(mobile);
        Order u=orderService.getOrderByEntity(order);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }




    /**
     *订单订单
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
     public @ResponseBody
     Map add(HttpSession session, String type, String startTime, String tenancy, String startAddress, String endAddress, String fetchSend, String flightTrain, String single, String status, String remark, String customerId,String customerType, String customerName, String customerMobile)
            throws Exception {
        Map result = new HashMap();
        OrderEx order = new OrderEx();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        order.setId(uuid);
        Customer customer=new Customer();
        customer.setId(customerId);
        Customer cr=customerService.getCustomerByEntity(customer);
        String orderNo=adminService.getSeqNextByName("seq_order_no");
        order.setOrderNo(orderNo);
        order.setType(type);
        order.setStatus(status);
        order.setStartTime(DateUtil.convert2Date(startTime,"yyyy-MM-dd HH:mm"));
        order.setStartAddress(startAddress);
        order.setEndAddress(endAddress);
        if(type.equals(String.valueOf(OrderType.FIGHT.getIndex()))||type.equals(String.valueOf(OrderType.TRAIN.getIndex()))){
            order.setFetchSend(fetchSend);
            order.setFlightTrain(flightTrain);
            order.setSingle(single);
        }else{
            order.setTenancy(tenancy);
        }
        order.setCustomerId(customerId);
        order.setCustomerType(customerType);
        order.setCustomerName(customerName);
        order.setCustomerMobile(customerMobile);
        if (StringUtil.isNotEmpty(remark)){
            order.setRemark(remark);
        }
        User user=(User)session.getAttribute("loginUser");
        /*User user=new User();
        user.setId("1");
        user.setLoginName("zf");
        user.setLoginName("张飞");
*/
        orderService.addOrder(order,user);
        result.put("result","success");
        return result;
    }

    /**
     *订单订单
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/addEdit")
    public @ResponseBody
    Map addEdit(HttpSession session, String id,String type, String startTime, String tenancy, String startAddress, String endAddress, String fetchSend, String flightTrain, String single, String status, String remark, String customerId,String customerType, String customerName, String customerMobile)
            throws Exception {
        Map result = new HashMap();
        OrderEx order = new OrderEx();
        order.setId(id);
        Customer customer=new Customer();
        customer.setId(customerId);
        Customer cr=customerService.getCustomerByEntity(customer);
        order.setType(type);
        order.setStartTime(DateUtil.convert2Date(startTime,"yyyy-MM-dd HH:mm"));
        order.setStartAddress(startAddress);
        order.setEndAddress(endAddress);
        if(type.equals(String.valueOf(OrderType.FIGHT.getIndex()))||type.equals(String.valueOf(OrderType.TRAIN.getIndex()))){
            order.setFetchSend(fetchSend);
            order.setFlightTrain(flightTrain);
            order.setSingle(single);
        }else{
            order.setTenancy(tenancy);
        }
        order.setCustomerId(customerId);
        order.setCustomerType(customerType);
        order.setCustomerName(customerName);
        order.setCustomerMobile(customerMobile);
        if (StringUtil.isNotEmpty(remark)){
            order.setRemark(remark);
        }
        User user=(User)session.getAttribute("loginUser");
    /*    User user=new User();
        user.setId("1");
        user.setLoginName("zf");
        user.setLoginName("张飞");*/

        orderService.modifyOrder(order,user);
        result.put("result","success");
        return result;
    }

    /**
     *订单预约
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/dispatch")
    public @ResponseBody

    Map dispatch(HttpSession session,String id,String driverId,String driverName,String driverMobile,
                 String carId,String plateNumber,String carType,
                 String supplierId,String supplierName,
                 String remark,String status,String action)throws Exception
    {
        Map result=new HashMap();
        OrderEx order=new OrderEx();
        order.setId(id);
        if(!action.equals("dispatchEdit")){
            order.setStatus(status);
        }
        if(!status.equals(String.valueOf(OrderStatus.CANCEL.getIndex()))) {
            order.setDriverId(driverId);
            order.setDriverName(driverName);
            order.setDriverMobile(driverMobile);
            order.setCarType(carType);
            order.setCarId(carId);
            order.setPlateNumber(plateNumber);
            order.setSupplierId(supplierId);
            order.setSupplierName(supplierName);
            if (StringUtil.isNotEmpty(remark)) {
                order.setRemark(remark);
            }
        }
        User user=(User)session.getAttribute("loginUser");
       /* User user=new User();
        user.setId("1");
        user.setLoginName("zf");
        user.setLoginName("张飞");*/
        orderService.modifyOrder(order,user);
        result.put("result","success");
        return result;
    }

    /**
     *订单完成
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/finish")
    public @ResponseBody

    Map finish(HttpSession session,String id,String total,String fee,String commission,
                 String remark,String status,String action)throws Exception
    {
        Map result=new HashMap();
        OrderEx order=new OrderEx();
        order.setId(id);
        if(!action.equals("finishEdit")){
            order.setStatus(status);
        }
        if(!status.equals(String.valueOf(OrderStatus.CANCEL.getIndex()))) {
            order.setTotal(new BigDecimal(total));
            order.setFee(new BigDecimal(fee));
            order.setCommission(new BigDecimal(commission));
        }
        if (StringUtil.isNotEmpty(remark)) {
            order.setRemark(remark);
        }
        User user=(User)session.getAttribute("loginUser");
       /* User user=new User();
        user.setId("1");
        user.setLoginName("zf");
        user.setLoginName("张飞");*/
        orderService.modifyOrder(order,user);
        result.put("result","success");
        return result;
    }


    /**
     *订单完成
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/terminal")
    public @ResponseBody

    Map terminal(HttpSession session,String id,String remark,String status)throws Exception
    {
        Map result=new HashMap();
        OrderEx order=new OrderEx();
        order.setId(id);
        order.setStatus(status);
        if (StringUtil.isNotEmpty(remark)) {
            order.setRemark(remark);
        }
        User user=(User)session.getAttribute("loginUser");
      /*  User user=new User();
        user.setId("1");
        user.setLoginName("zf");
        user.setLoginName("张飞");*/
        orderService.modifyOrder(order,user);
        result.put("result","success");
        return result;
    }

    @RequestMapping(value="/export")
    public ModelAndView export(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                               HttpSession session) {
        ViewExcel viewExcel = new ViewExcel();
        Map obj = new HashMap();
        System.out.println("response:" + response);
        //获取数据库表生成的workbook
        Map condition = new HashMap();
        //这里是从数据库里查数据并组装成我们想要的数据结构的过程，略。。

        String beginTime,endTime,customerName,suppplierName;
        beginTime=(String)session.getAttribute("beginTime");
        endTime=(String)session.getAttribute("endTime");
        customerName=(String)session.getAttribute("customerName");
        suppplierName=(String)session.getAttribute("suppplierName");

        OrderEx orderEx=new OrderEx();
        if(StringUtil.isNotEmpty(beginTime)) {
            orderEx.setBeginTime(beginTime);
            session.removeAttribute("beginTime");
        }
        if(StringUtil.isNotEmpty(endTime)) {
            orderEx.setEndTime(endTime);
            session.removeAttribute("endTime");
        }
        if(StringUtil.isNotEmpty(customerName)) {
            orderEx.setCustomerName(customerName);
            session.removeAttribute("customerName");
        }
        if(StringUtil.isNotEmpty(suppplierName)) {
            orderEx.setSupplierName(suppplierName);
            session.removeAttribute("suppplierName");
        }
        orderEx.setStatus("5");

        List<OrderEx> list=orderService.getOrderPageByEntity(orderEx);
        String[] headers={"客户名称","客户电话","客户类型",
                         "订单类型","用车时间", "租期","接/送","单程/往返","航班/车次",
                         "出发地点","送达地点",
                         "费用","佣金","订单金额",
                         "车型","车牌","供应商",
                         "司机", "司机电话",
                          "状态","备注"};
        String[] ds_titles = { "customerName","customerMobile","customerType",
                               "orderType","startTime", "tenancy","fetchSend","single","flightTrain",
                               "startAddress","endAddress",
                               "fee" ,"commission" ,"total",
                                "carType","plateNumber","supplierName",
                                "driverName", "driverMobile"
                                ,"status","remark"};



        int[] ds_format = { 1, 1,1,1,
                            1,1,1,1,1,1,
                            1,1,
                            1,1,1,
                            1,1,1,
                            1,1,
                            1,1};
        List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
        //if(CollectionUtils.isNotEmpty(dataset)){
        for(Order order :list){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("customerName",order.getCustomerName());
            map.put("customerMobile",order.getCustomerMobile());
            map.put("customerType", CustomerType.getName(Integer.valueOf(order.getCustomerType())));

            map.put("orderType", OrderType.getName(Integer.valueOf(order.getType())));
            map.put("startTime", DateUtil.convert2String(order.getStartTime(), "yyyy-MM-dd HH:mm"));
            map.put("tenancy", order.getTenancy());
            map.put("fetchSend",  SendType.getName(Integer.valueOf(order.getFetchSend())));
            map.put("single",  SingleType.getName(Integer.valueOf(order.getSingle())));
            map.put("flightTrain", order.getFlightTrain());

            map.put("startAddress", order.getStartAddress());
            map.put("endAddress", order.getEndAddress());
            map.put("fee", order.getFee());
            map.put("commission", order.getCommission());
            map.put("total", order.getTotal());

            map.put("carType",order.getCarType());
            map.put("plateNumber", order.getPlateNumber());
            map.put("supplierName",order.getSupplierName());


            map.put("driverName", order.getDriverName());
            map.put("driverMobile", order.getDriverMobile());

            map.put("status", "订单终结");
            map.put("remark", order.getRemark());


            data.add(map);
        }
        // }
        String filename="订单导出.xls";
        obj.put("filename",filename);
        try {
            HSSFWorkbook workbook = ExcelExportUtil.export("", "sheet1", headers, ds_titles, ds_format, null, data);
            viewExcel.buildExcelDocument(obj, workbook, request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView(viewExcel, model);
    }




    public IOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public IAdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(IAdminService adminService) {
        this.adminService = adminService;
    }
}
