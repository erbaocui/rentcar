package com.cn.controller;

import com.cn.util.ExcelExportUtil;
import com.cn.util.Student;
import com.cn.view.ViewExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by home on 2017/7/28.
 */
@Controller
@RequestMapping("/excel")
@Scope("prototype")
public class ExportExcel extends BaseController{


    @RequestMapping(value="/export")
    public ModelAndView export(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ViewExcel viewExcel = new ViewExcel();
        Map obj = null;
        System.out.println("response:" + response);
        //获取数据库表生成的workbook
        Map condition = new HashMap();
        //这里是从数据库里查数据并组装成我们想要的数据结构的过程，略。。
        String[] headers =
                { "学号", "姓名", "年龄", "性别", "出生日期" };

        List<Student> dataset = new ArrayList<Student>();
        dataset.add(new Student(10000001, "张三", 20, true, new Date()));
        dataset.add(new Student(20000002, "李四", 24, false, new Date()));
        dataset.add(new Student(30000003, "王五", 22, true, new Date()));
        String[] excelHeader = { "编号", "姓名", "年龄","性别","生日"};
        String[] ds_titles = { "id", "name", "age","gender","birthday"};
        int[] ds_format = { 1, 1, 1,1,1};
        int[] widths = { 100, 100,100,100,100};
        List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
        //if(CollectionUtils.isNotEmpty(dataset)){
            for(Student student :dataset){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("id", student.getId());
                map.put("name", student.getName());
                map.put("age", student.getAge());
                map.put("gender", student.getSex());
                map.put("birthday",new Date());
                data.add(map);
            }
       // }

        try {
            HSSFWorkbook workbook = ExcelExportUtil.export("", "sheet1",headers,ds_titles,ds_format,null,data);
            viewExcel.buildExcelDocument(obj, workbook, request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView(viewExcel, model);
    }
}
