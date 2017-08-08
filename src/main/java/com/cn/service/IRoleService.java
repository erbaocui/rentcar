package com.cn.service;

import com.cn.model.Menu;
import com.cn.model.Role;
import com.cn.model.RoleMenu;
import com.cn.vo.MenuEx;

import java.util.List;

public interface IRoleService {

  //权限
    public List<Menu> getMenuItemByParenId(String parentId);
    public List<Role> queryRolePageByEntity(Role role);
    public List<Role> queryAllRole();
    public Role queryRoleByEntity(Role role);
    public void addRole(Role role, List<RoleMenu> list)throws Exception;
    public void modifyRole(Role role, List<RoleMenu> list)throws Exception;
    public void deleteRole(Role role)throws Exception;
    public List<MenuEx> queryRoleMenuByEntity(RoleMenu roleMenu);
    public List<MenuEx> queryRoleMenuShowByEntity(RoleMenu roleMenu);
}
