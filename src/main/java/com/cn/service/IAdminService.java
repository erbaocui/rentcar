package com.cn.service;

import com.cn.model.Menu;

import java.util.List;

public interface IAdminService {
   //菜单
    public List<Menu> getMenuItemByParenId(String parentId);
    public String  getSeqNextByName(String seqName );
}
