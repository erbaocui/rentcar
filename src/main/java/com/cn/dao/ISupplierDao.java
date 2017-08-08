package com.cn.dao;

import com.cn.model.Supplier;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ISupplierDao {


    public List<Supplier> pageList(Supplier supplier);
    public List<Supplier> allValidList();
    public void insert(Supplier supplier);
    public void update(Supplier supplier);
    public Supplier find(Supplier supplier);
    public void delete(String  supplierId);


}