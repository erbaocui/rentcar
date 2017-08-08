package com.cn.dao.impl;

import com.cn.dao.IMenuDao;
import com.cn.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by home on 2017/7/7.
 */

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl implements IMenuDao{


    @Override
    public List<Menu> getMenuChild(String parentId) {
        return (List<Menu>)list("com.cn.dao.MenuMapper.selectByParentId",parentId);
    }
}
