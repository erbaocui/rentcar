package com.cn.dao;

import com.cn.model.RoleMenu;
import com.cn.vo.MenuEx;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IRoleMenuDao {


    public void insertBatch(List<RoleMenu> list);
    public void delete(String roleId);
    public List<MenuEx> selectRoleMenuList(RoleMenu roleMenu);
    public List<MenuEx> selectRoleMenuShowList(RoleMenu roleMenu);



}