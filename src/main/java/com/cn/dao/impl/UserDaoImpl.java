package com.cn.dao.impl;

import com.cn.dao.IMenuDao;
import com.cn.dao.IUserDao;
import com.cn.model.Menu;
import com.cn.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements IUserDao {

    @Override
    public List<User> pageList(User user) {
        return (List<User>)list("com.cn.dao.UserMapper.selectPageByEntity", user);

    }

    @Override
    public void insert(User user) {
        addObject("com.cn.dao.UserMapper.insert", user);
    }

    @Override
    public void update(User user) {
        updateObject("com.cn.dao.UserMapper.updateByPrimaryKey", user);
    }

    @Override
    public User find(User user) {
        return (User)findObject("com.cn.dao.UserMapper.selectOneByEntity", user);
    }
}
