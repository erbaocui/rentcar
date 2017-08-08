package com.cn.dao;

import com.cn.model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by home on 2017/7/2.
 */



public interface IMenuDao{


    public List<Menu> getMenuChild(String parentId);


}