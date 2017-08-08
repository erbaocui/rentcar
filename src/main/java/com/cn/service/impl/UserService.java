package com.cn.service.impl;

import com.cn.dao.IUserDao;
import com.cn.model.User;
import com.cn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("userService")
public class UserService implements IUserService {


    @Autowired
    private IUserDao userDao;



    @Override
    public List<User> getUserPageByEntity(User user){
        return userDao.pageList(user);
    }
    @Override
    public User getUserByEntity(User user) {
        return userDao.find(user);
    }
    @Override
    public void addUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }



    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}
