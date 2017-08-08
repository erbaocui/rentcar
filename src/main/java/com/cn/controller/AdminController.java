package com.cn.controller;

import com.cn.model.RoleMenu;
import com.cn.model.User;
import com.cn.service.IRoleService;
import com.cn.service.IUserService;
import com.cn.util.MD5Util;

import com.cn.vo.MenuEx;
import com.cn.vo.TreeItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminController extends BaseController{

    Logger logger= Logger.getLogger(AdminController.class.getName());


    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * 登录
     * @param session
     *          HttpSession
     * @param
     *           userName
     * @param password
     *          密码
     * @return
     */
    @RequestMapping(value="/login")
    public ModelAndView login(HttpSession session,String userName,String password) throws Exception{
        //在Session里保存信息
        User user=new User();
        user.setLoginName(userName);
        User u=userService.getUserByEntity(user);
        ModelAndView modelAndView = new ModelAndView();
        if(null!=u&&u.getPassword().equals(MD5Util.md5(password))){
            session.setAttribute("loginUser",u);
            modelAndView=new ModelAndView("redirect:/admin/main.do");
        }else{
            modelAndView.addObject("errorMsg","用户名密码错误");
            modelAndView.setViewName("/login");
        }

        return modelAndView;

    }
    @RequestMapping(value="/main")
    public ModelAndView main(HttpSession session) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        User user=(User)session.getAttribute("loginUser");
        /*User user=new User();
        user.setId("1");
        user.setLoginName("zf");
        user.setDisplayName("张飞");*/
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/main");
        return modelAndView;
    }


    /**
     * 退出系统
     * @param session
     *          Session
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();
        return "redirect:/page/login.jsp";
    }

    /**
     * 菜单选择
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/menu")
    public @ResponseBody
    List<TreeItem>  getMenu(HttpSession session)
    {
            List<TreeItem> result=new ArrayList<TreeItem>();
            String parentId="1";
            TreeItem  rootItem =new TreeItem();
            rootItem.setChecked(false);
            rootItem.setId("1");
            rootItem.setText("菜单");
            rootItem.setState("open");
            User user=(User)session.getAttribute("loginUser");
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setMenuId(parentId);
            //roleMenu.setRoleId(user.getRoleId());
            roleMenu.setRoleId("b89a21ffe0044a7f82112e2d686949aa");
            roleMenu.setMenuId(parentId);
            Map rootAttributes=new HashMap();
            rootAttributes.put("url","");
            rootAttributes.put("isLeaf","1");
            rootItem.setAttributes(rootAttributes);
            List<MenuEx> menuList=roleService.queryRoleMenuShowByEntity(roleMenu);
            List<TreeItem> oneLeveChildren=new ArrayList<TreeItem>();
            for(MenuEx menu:menuList){
                TreeItem  oneLeveItem =new TreeItem();
                oneLeveItem.setChecked(false);
                oneLeveItem.setId(menu.getId());
                oneLeveItem.setText(menu.getName());
                oneLeveItem.setState("open");

                Map oneLeveIAttributes=new HashMap();
                oneLeveIAttributes.put("url",menu.getUrl());
                oneLeveIAttributes.put("isLeaf",menu.getIsLeaf());
                oneLeveItem.setAttributes(oneLeveIAttributes);
                roleMenu.setMenuId(menu.getId());
                List<MenuEx> leafList=roleService.queryRoleMenuShowByEntity(roleMenu);
                List<TreeItem> twoLeveChildren=new ArrayList<TreeItem>();
                for(MenuEx leaf: leafList){
                    TreeItem  twoLeveItem =new TreeItem();
                    twoLeveItem.setChecked(false);
                    twoLeveItem.setId(leaf.getId());
                    twoLeveItem.setText(leaf.getName());
                    twoLeveItem.setState("open");
                    Map  twoLeveIAttributes=new HashMap();
                    twoLeveIAttributes.put("url",leaf.getUrl());
                    twoLeveIAttributes.put("isLeaf",leaf.getIsLeaf());
                    twoLeveItem.setAttributes(twoLeveIAttributes);
                    twoLeveChildren.add(twoLeveItem);
                }
                oneLeveItem.setChildren(twoLeveChildren);
                oneLeveChildren.add(oneLeveItem);
            }
            rootItem.setChildren(oneLeveChildren);
            result.add(rootItem);
            return result;

    }



    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
