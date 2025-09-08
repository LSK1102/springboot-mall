package com.lsk.springbootmall.dao;

import com.lsk.springbootmall.constant.ProductCategory;
import com.lsk.springbootmall.dto.ProductRequest;
import com.lsk.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productid);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);
}

