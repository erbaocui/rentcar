package com.cn.controller;

import com.cn.model.Picture;
import com.cn.service.IPictureService;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/picture")
@Scope("prototype")
public class PictureController extends BaseController{

    Logger logger= Logger.getLogger(PictureController.class.getName());

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private HttpServletRequest request;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 旅游图片管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/tour")
    public String tour() throws Exception{
        return "redirect:/page/picture/tourList.jsp";
    }
    /**
     *签证图片管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/visa")
    public String visa() throws Exception{
        return "redirect:/page/picture/visaList.jsp";
    }

    /**
     *签证图片管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/rv")
    public String rv() throws Exception{
        return "redirect:/page/picture/rvList.jsp";
    }

    /**
     * 图片列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map pictureList(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String type,String name,String status)throws Exception
    {
         PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Picture picture=new Picture();
        if(StringUtil.isNotEmpty(type)){
            picture.setType(type);
        }
        if(StringUtil.isNotEmpty(name)){
            picture.setName(name);
        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                picture.setStatus(status);
            }
        }



        List<Picture> list=pictureService.getPicturePageByEntity(picture);

        PageInfo<Picture>p=new PageInfo<Picture>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     * 图片查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String loginName)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Picture picture=new Picture();
        Picture u=pictureService.getPictureByEntity(picture);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }


    /**
     * 图片添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String name,String type,Integer seq,String url,String remark)throws Exception
    {
        Map result=new HashMap();
        Picture picture=new Picture();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        picture.setId(uuid);
        picture.setName(name);
        picture.setType(type);
        picture.setSeq(seq);
        picture.setUrl(url);
        picture.setRemark(remark);
        picture.setStatus("0");
        pictureService.addPicture(picture);
        result.put("result","success");
         return result;
    }

    /**
     * 图片编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String name,String type,Integer seq,String status,String url,String remark )throws Exception
    {
        Map result=new HashMap();
        Picture picture=new Picture();
        picture.setId(id);
        picture.setName(name);
        picture.setType(type);
        picture.setSeq(seq);
        picture.setUrl(url);
        picture.setRemark(remark);
        picture.setStatus(status);
        pictureService.modifyPicture(picture);
        result.put("result","success");
        return result;
    }
    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map delete(String id)throws Exception
    {
        Map result=new HashMap();

        Picture picture=new Picture();
        picture.setId(id);
        pictureService.deletePicture(picture);

        result.put("result","success");
        return result;
    }

    public IPictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(IPictureService pictureService) {
        this.pictureService = pictureService;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
