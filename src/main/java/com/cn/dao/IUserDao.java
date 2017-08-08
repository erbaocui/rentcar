package com.cn.dao;

import com.cn.model.Menu;
import com.cn.model.User;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IUserDao {


    public List<User> pageList(User user);
    public void insert(User user);
    public void update(User user);
    public User find(User user);


}