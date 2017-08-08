package com.cn.service;

import com.cn.model.User;

import java.util.List;

public interface IUserService {
    //用户
    public List<User> getUserPageByEntity(User user);
    public User getUserByEntity(User user);
    public void addUser(User user);
    public void modifyUser(User user);

}
