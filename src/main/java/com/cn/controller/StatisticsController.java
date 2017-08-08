package com.cn.controller;

import com.cn.constant.OrderStatus;
import com.cn.constant.OrderType;
import com.cn.model.*;
import com.cn.service.ICustomerService;
import com.cn.service.IOrderService;
import com.cn.service.IStatisticsService;
import com.cn.service.IUserService;
import com.cn.util.DateUtil;
import com.cn.util.ExcelExportUtil;
import com.cn.util.StringUtil;
import com.cn.util.Student;
import com.cn.view.ViewExcel;
import com.cn.vo.OrderEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@RequestMapping("/statistics")
@Scope("prototype")
public class StatisticsController extends BaseController{

    Logger logger= Logger.getLogger(StatisticsController.class.getName());

    @Autowired
    private IStatisticsService statisticsService;



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
    @RequestMapping(value="/statistics")
    public String person() throws Exception{
        return "redirect:/page/statistics/statisticsList.jsp";
    }
   

    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String beginTime,String endTime,String groupType,HttpSession session)throws Exception
    {

        OrderEx order=new OrderEx();
        Map map=new HashMap();
        if(StringUtil.isEmpty(beginTime)&&StringUtil.isEmpty(endTime)){
            map.put("total","0");
            map.put("rows",new ArrayList());
            return map;
        }
        if(StringUtil.isNotEmpty(beginTime)) {
            order.setBeginTime(beginTime+" 00:00:00");
            session.setAttribute("beginTime",beginTime+" 00:00:00");
        }
        if(StringUtil.isNotEmpty(endTime)) {
            order.setEndTime(endTime+" 59:59:59");
            session.setAttribute("endTime",endTime+" 59:59:59");
        }
        order.setGroupType(groupType);
        session.setAttribute("groupType",groupType);
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(rows));
        List<Statistics> list=statisticsService.getPageList(order);
        PageInfo<Statistics>p=new PageInfo<Statistics>(list);

        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
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

        String beginTime,endTime,groupType;
        groupType=(String)session.getAttribute("groupType");
        beginTime=(String)session.getAttribute("beginTime");
        endTime=(String)session.getAttribute("endTime");
        OrderEx order=new OrderEx();
        if(StringUtil.isNotEmpty(beginTime)) {
            order.setBeginTime(beginTime);
            session.removeAttribute("beginTime");
        }
        if(StringUtil.isNotEmpty(endTime)) {
            order.setEndTime(endTime);
            session.removeAttribute("endTime");
        }
        order.setGroupType(groupType);
        List<Statistics> list=statisticsService.getPageList(order);
        Statistics sum=statisticsService.getSum(order);
        String[] headers={"客户", "费用", "订单金额合计","佣金"};
        if(groupType.equals("1")){
            headers[0]="供应商";
        }
        String[] ds_titles = { "statistics", "sumFee", "sumTotal","sumCommission"};



        int[] ds_format = { 1, 1, 1,1,1};
        List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
        //if(CollectionUtils.isNotEmpty(dataset)){
        for(Statistics statistics :list){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("statistics", statistics.getStatistics());
            map.put("sumFee", statistics.getSumFee());
            map.put("sumTotal",statistics.getSumTotal());
            map.put("sumCommission",statistics.getSumCommission());
            data.add(map);
        }
        // }
        String filename="tongji.xls";
        obj.put("filename",filename);
        try {
            HSSFWorkbook workbook = ExcelExportUtil.export("", "sheet1", headers, ds_titles, ds_format, null, data);
            HSSFSheet sheet=workbook.getSheet("sheet1");
            HSSFRow row=sheet.createRow((short) (sheet.getLastRowNum() + 1));
            row.createCell(0).setCellValue("合计");
            row.createCell(1).setCellValue(String.valueOf(sum.getSumFee()));
            row.createCell(2).setCellValue(String.valueOf(sum.getSumTotal()));
            row.createCell(3).setCellValue(String.valueOf(sum.getSumCommission()));
            viewExcel.buildExcelDocument(obj, workbook, request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView(viewExcel, model);
    }

    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}
