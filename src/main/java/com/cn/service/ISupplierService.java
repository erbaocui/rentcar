package com.cn.service;

import com.cn.model.Supplier;

import java.util.List;

public interface ISupplierService {
    //用户
    public List<Supplier> getSupplierPageByEntity(Supplier supplier);
    public List<Supplier> getAllValidSupplier();
    public Supplier getSupplierByEntity(Supplier supplier);
    public void addSupplier(Supplier supplier);
    public void modifySupplier(Supplier supplier);
    public void deleteSupplier(Supplier supplier);

}
