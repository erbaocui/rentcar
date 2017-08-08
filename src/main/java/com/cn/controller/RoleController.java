package com.cn.controller;

import com.cn.model.Menu;
import com.cn.model.Role;
import com.cn.model.RoleMenu;
import com.cn.model.User;
import com.cn.service.IRoleService;
import com.cn.service.IUserService;
import com.cn.util.StringUtil;

import com.cn.vo.MenuEx;
import com.cn.vo.TreeItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/role")
@Scope("prototype")
public class RoleController extends BaseController{

    Logger logger= Logger.getLogger(RoleController.class.getName());

    @Autowired

    private IRoleService roleService;
    @Autowired
    private IUserService userService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }




    /**
     * 角色管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/role")
    public String role() throws Exception{
        return "redirect:/page/admin/roleList.jsp";
    }

    /**
     * 角色列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
                 String kv,String name)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Role role=new Role();
        if(StringUtil.isNotEmpty(name)){
            role.setName(name);
        }
        if(StringUtil.isNotEmpty(kv)){
            role.setKv(kv);
        }
        List<Role> list=roleService.queryRolePageByEntity(role);

        PageInfo<Role>p=new PageInfo<Role>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }
    /**
     * 角色查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String kv)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Role role=new Role();
        role.setKv(kv);
        Role r=roleService.queryRoleByEntity(role);
        if(null!=r){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }
    /**
     * 角色列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/all")
    public @ResponseBody
    Map all()throws Exception
    {
        List<Role> list=roleService.queryAllRole();
        Map map=new HashMap();
        map.put("rows",list);
        return map;
    }

    /**
     * 角色权限选择
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/addRightTree")
    public @ResponseBody
    List<TreeItem> addRightTree()
    {
        List<TreeItem> result=new ArrayList<TreeItem>();
        String parentId="1";
        TreeItem  rootItem =new TreeItem();
        rootItem.setChecked(false);
        rootItem.setId("1");
        rootItem.setText("菜单");
        rootItem.setState("open");
        List<Menu> menuList=roleService.getMenuItemByParenId(parentId);
        List<TreeItem> oneLeveChildren=new ArrayList<TreeItem>();
        for(Menu menu:menuList){
            TreeItem  oneLeveItem =new TreeItem();
            oneLeveItem.setChecked(false);
            oneLeveItem.setId(menu.getId());
            oneLeveItem.setText(menu.getName());
            oneLeveItem.setState("open");
            List<Menu> leafList=roleService.getMenuItemByParenId(menu.getId());
            List<TreeItem> twoLeveChildren=new ArrayList<TreeItem>();
            for(Menu leaf: leafList){
                TreeItem  twoLeveItem =new TreeItem();
                twoLeveItem.setChecked(false);
                twoLeveItem.setId(leaf.getId());
                twoLeveItem.setText(leaf.getName());
                twoLeveItem.setState("open");
                twoLeveChildren.add(twoLeveItem);
            }
            oneLeveItem.setChildren(twoLeveChildren);
            oneLeveChildren.add(oneLeveItem);
        }
        rootItem.setChildren(oneLeveChildren);
        result.add(rootItem);
        return result;
    }

    /**
     * 角色权限选择
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/modifyRightTree")
    public @ResponseBody
    List<TreeItem>modifyRightTree(String roleId)
    {
        List<TreeItem> result=new ArrayList<TreeItem>();
        String parentId="1";
        TreeItem  rootItem =new TreeItem();
        rootItem.setChecked(false);
        rootItem.setId("1");
        rootItem.setText("菜单");
        rootItem.setState("open");
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setMenuId(parentId);
        roleMenu.setRoleId(roleId);
        List<MenuEx> menuList=roleService.queryRoleMenuByEntity(roleMenu);
        List<TreeItem> oneLeveChildren=new ArrayList<TreeItem>();
        for(MenuEx menu:menuList){
            TreeItem  oneLeveItem =new TreeItem();
            oneLeveItem.setChecked(false);
            oneLeveItem.setId(menu.getId());
            oneLeveItem.setText(menu.getName());
            oneLeveItem.setState("open");
            roleMenu.setMenuId(menu.getId());
            List<MenuEx> leafList=roleService.queryRoleMenuByEntity(roleMenu);
            List<TreeItem> twoLeveChildren=new ArrayList<TreeItem>();
            for(MenuEx leaf: leafList){
                TreeItem  twoLeveItem =new TreeItem();
                if(leaf.getRoleId()!=null&&leaf.getIsLeaf().equals("0")){
                    twoLeveItem.setChecked(true);
                }else{
                    twoLeveItem.setChecked(false);
                }
                twoLeveItem.setId(leaf.getId());
                twoLeveItem.setText(leaf.getName());
                twoLeveItem.setState("open");
                twoLeveChildren.add(twoLeveItem);
            }
            oneLeveItem.setChildren(twoLeveChildren);
            oneLeveChildren.add(oneLeveItem);
        }
        rootItem.setChildren(oneLeveChildren);
        result.add(rootItem);
        return result;
    }

    /**
     * 角色权限选择
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/showRightTree")
    public @ResponseBody
    List<TreeItem>showRightTree(String roleId)
    {
        List<TreeItem> result=new ArrayList<TreeItem>();
        String parentId="1";
        TreeItem  rootItem =new TreeItem();
        rootItem.setChecked(false);
        rootItem.setId("1");
        rootItem.setText("菜单");
        rootItem.setState("open");
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setMenuId(parentId);
        roleMenu.setRoleId(roleId);
        List<MenuEx> menuList=roleService.queryRoleMenuShowByEntity(roleMenu);
        List<TreeItem> oneLeveChildren=new ArrayList<TreeItem>();
        for(MenuEx menu:menuList){
            TreeItem  oneLeveItem =new TreeItem();
            oneLeveItem.setChecked(false);
            oneLeveItem.setId(menu.getId());
            oneLeveItem.setText(menu.getName());
            oneLeveItem.setState("open");
            roleMenu.setMenuId(menu.getId());
            List<MenuEx> leafList=roleService.queryRoleMenuShowByEntity(roleMenu);
            List<TreeItem> twoLeveChildren=new ArrayList<TreeItem>();
            for(MenuEx leaf: leafList){
                TreeItem  twoLeveItem =new TreeItem();
                if(leaf.getRoleId()!=null&&leaf.getIsLeaf().equals("0")){
                    twoLeveItem.setChecked(true);
                }else{
                    twoLeveItem.setChecked(false);
                }
                twoLeveItem.setId(leaf.getId());
                twoLeveItem.setText(leaf.getName());
                twoLeveItem.setState("open");
                twoLeveChildren.add(twoLeveItem);
            }
            oneLeveItem.setChildren(twoLeveChildren);
            oneLeveChildren.add(oneLeveItem);
        }
        rootItem.setChildren(oneLeveChildren);
        result.add(rootItem);
        return result;
    }



    /**
     * 角色修改
     * @param
     *
     * @return success
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody

    Map modify(String rightIds,String key,String name,String remark,String id) throws Exception
    {
        Map result=new HashMap();
        Role role=new Role();
        role.setId(id);
        role.setName(name);
        role.setRemark(remark);
        String[] rightArray=rightIds.split(",");
        List<RoleMenu> list=new ArrayList<RoleMenu>();
        for(String rihgt:rightArray){
            RoleMenu   roleMenu =new RoleMenu();
            roleMenu.setMenuId(rihgt);
            roleMenu.setRoleId(id);
            roleMenu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            list.add(roleMenu);
        }
        roleService.modifyRole(role,list);
        result.put("result","success");
        return result;
    }
    /**
     * 角色保存
     * @param
     *
     * @return success
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String rightIds,String kv,String name,String remark)  throws Exception
    {
        Map result=new HashMap();
        Role role=new Role();
        String roleId=UUID.randomUUID().toString().replaceAll("-", "");
        role.setId(roleId);
        role.setKv(kv);
        role.setName(name);
        role.setRemark(remark);
        String[] rightArray=rightIds.split(",");
        List<RoleMenu> list=new ArrayList<RoleMenu>();
        for(String rihgt:rightArray){
            RoleMenu   roleMenu =new RoleMenu();
            roleMenu.setMenuId(rihgt);
            roleMenu.setRoleId(roleId);
            roleMenu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            list.add(roleMenu);
        }
        roleService.addRole(role,list);
        result.put("result","success");
        return result;
    }
    /**
     * 角色删除
     * @param
     *
     * @return success
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map delete(String roleId) throws Exception
    {
        Map result=new HashMap();
        User user=new User();
        Role role=new Role();
        role.setId(roleId);
        user.setRole(role);
        User u=userService.getUserByEntity(user);
        if(null!=u){
            result.put("result","have");
        }else{
            role=new Role();
            role.setId(roleId);
            roleService.deleteRole(role);
            result.put("result","success");
        }
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
