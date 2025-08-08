package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dao.ProductDao;
import com.lsk.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServicelmpl implements ProductService {


    @Autowired
    private ProductDao productDao;


    @Override
    public Product getProductById(Integer productid) {

        return productDao.getProductById(productid);
    }
}
