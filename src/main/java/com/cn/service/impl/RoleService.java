package com.cn.service.impl;

import com.cn.dao.IMenuDao;
import com.cn.dao.IRoleDao;
import com.cn.dao.IRoleMenuDao;
import com.cn.model.Menu;
import com.cn.model.Role;
import com.cn.model.RoleMenu;
import com.cn.service.IRoleService;
import com.cn.vo.MenuEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("roleService")
public class RoleService implements IRoleService {

    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IRoleMenuDao roleMenuDao;
    @Override
    public List<Menu>  getMenuItemByParenId(String parentId) {
        return  menuDao.getMenuChild(parentId);
    }

    @Override
    public List<Role> queryRolePageByEntity(Role role) {
        return roleDao.pageList(role);
    }

    @Override
    public Role queryRoleByEntity(Role role) {
        return roleDao.find(role);
    }

    @Override
    public List<Role> queryAllRole() {
        return roleDao.selectAllList();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void addRole(Role role, List<RoleMenu> list) throws Exception{
        roleMenuDao.insertBatch(list);
        roleDao.insert(role);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void deleteRole(Role role)throws Exception {
        roleMenuDao.delete(role.getId());
        roleDao.delete(role.getId());
    }

    @Override
      public List<MenuEx> queryRoleMenuByEntity(RoleMenu roleMenu) {
        return roleMenuDao.selectRoleMenuList(roleMenu);
    }
    @Override
    public List<MenuEx> queryRoleMenuShowByEntity(RoleMenu roleMenu) {
        return roleMenuDao.selectRoleMenuShowList(roleMenu);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void modifyRole(Role role, List<RoleMenu> list)throws Exception {
        roleMenuDao.delete(role.getId());
        roleMenuDao.insertBatch(list);
        roleDao.update(role);
    }

    public IMenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public IRoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public IRoleMenuDao getRoleMenuDao() {
        return roleMenuDao;
    }

    public void setRoleMenuDao(IRoleMenuDao roleMenuDao) {
        this.roleMenuDao = roleMenuDao;
    }
}
