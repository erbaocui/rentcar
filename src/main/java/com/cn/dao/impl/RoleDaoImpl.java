package com.cn.dao.impl;

import com.cn.dao.IRoleDao;
import com.cn.dao.IUserDao;
import com.cn.model.Role;
import com.cn.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl implements IRoleDao {

    @Override
    public List<Role> pageList(Role role) {
        return (List<Role>)list("com.cn.dao.RoleMapper.selectPageByEntity", role);

    }

    @Override
    public List<Role> selectAllList() {
        return (List<Role>)list("com.cn.dao.RoleMapper.selectAllRole", "");
    }

    @Override
    public void insert(Role role) {
        addObject("com.cn.dao.RoleMapper.insert", role);
    }


    @Override
    public Role find(Role role) {
        return (Role)findObject("com.cn.dao.RoleMapper.selectOneByEntity", role);
    }

    @Override
    public void update(Role role) {
         updateObject("com.cn.dao.RoleMapper.updateByPK",role);
    }

    @Override
    public void delete(String roleId) {
        deleteObject("com.cn.dao.RoleMapper.deleteByRoleId", roleId);
    }
}
