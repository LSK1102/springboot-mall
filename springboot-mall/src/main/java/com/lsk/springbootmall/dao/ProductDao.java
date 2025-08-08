package com.lsk.springbootmall.dao;

import com.lsk.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productid);
}
