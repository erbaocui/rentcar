package com.cn.service.impl;

import com.cn.dao.ISupplierDao;
import com.cn.model.Supplier;
import com.cn.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("supplierService")
public class SupplierService implements ISupplierService {


    @Autowired
    private ISupplierDao supplierDao;



    @Override
    public List<Supplier> getSupplierPageByEntity(Supplier supplier){
        return supplierDao.pageList(supplier);
    }

    @Override
    public List<Supplier> getAllValidSupplier() {
        return supplierDao.allValidList();
    }

    @Override
    public Supplier getSupplierByEntity(Supplier supplier) {
        return supplierDao.find(supplier);
    }
    @Override
    public void addSupplier(Supplier supplier) {
        supplierDao.insert(supplier);
    }

    @Override
    public void modifySupplier(Supplier supplier) {
        supplierDao.update(supplier);
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        supplierDao.delete(supplier.getId());
    }

    public ISupplierDao getSupplierDao() {
        return supplierDao;
    }

    public void setSupplierDao(ISupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

}
