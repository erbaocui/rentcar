package com.cn.controller;

import com.cn.model.Role;
import com.cn.model.User;
import com.cn.service.IUserService;
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

import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserController extends BaseController{

    Logger logger= Logger.getLogger(UserController.class.getName());

    @Autowired
    private IUserService userService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 用户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/user")
    public String user() throws Exception{
        return "redirect:/page/admin/userList.jsp";
    }

    /**
     * 用户列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String loginName,String displayName,String status)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        User user=new User();
        if(StringUtil.isNotEmpty(loginName)){
            user.setLoginName(loginName);
        }
        if(StringUtil.isNotEmpty(displayName)){
            user.setDisplayName(displayName);
        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                user.setStatus(status);
            }
        }
        List<User> list=userService.getUserPageByEntity(user);

        PageInfo<User>p=new PageInfo<User>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     * 用户查询
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
        User user=new User();
        user.setLoginName(loginName);
        User u=userService.getUserByEntity(user);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }


    /**
     * 用户添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String loginName,String displayName,String password,String remark,String roleId)throws Exception
    {
        Map result=new HashMap();
        User user=new User();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(uuid);
        user.setLoginName(loginName);
        user.setDisplayName(displayName);
        user.setRemark(remark);
        user.setStatus("0");
        Role role=new Role();
        role.setId(roleId);
        user.setRole(role);
        user.setPassword(MD5Util.md5(password));
        userService.addUser(user);
        result.put("result","success");
         return result;
    }

    /**
     * 用户编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String displayName,String status,String remark,String password)throws Exception
    {
        Map result=new HashMap();
        User user=new User();
        user.setId(id);
        user.setDisplayName(displayName);
        user.setRemark(remark);
        user.setStatus(status);
        user.setPassword(MD5Util.md5(password));
        userService.modifyUser(user);
        result.put("result","success");
        return result;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
