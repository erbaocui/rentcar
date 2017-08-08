package com.cn.dao;

import com.cn.model.Role;
import com.cn.model.User;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IRoleDao {


    public List<Role> pageList(Role role);
    public List<Role> selectAllList();
    public void insert(Role role);
    public void delete(String roleId);
    public void update(Role role);
    public Role find(Role role);



}