package com.cn.service.impl;

import com.cn.dao.IAdminDao;
import com.cn.dao.IMenuDao;
import com.cn.model.Menu;
import com.cn.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("adminService")
public class AdminService implements IAdminService{
    @Autowired
    private IAdminDao adminDao;
    @Autowired
    private IMenuDao menuDao;
    @Override
    public List<Menu>  getMenuItemByParenId(String parentId) {
        return  menuDao.getMenuChild(parentId);
    }

    @Override
    public String getSeqNextByName(String seqName) {
        String next=adminDao.find(seqName);
        if(next.length()==1){
            next="0000000"+next;
        }
        if(next.length()==2){
            next="000000"+next;
        }
        if(next.length()==3){
            next="00000"+next;
        }
        if(next.length()==4){
            next="0000"+next;
        }
        if(next.length()==5){
            next="000"+next;
        }
        if(next.length()==6){
            next="00"+next;
        }
        if(next.length()==7){
            next="0"+next;
        }
        next="NO"+next;
        return next;

    }
}
