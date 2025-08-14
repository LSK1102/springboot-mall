package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dto.ProductRequest;
import com.lsk.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productid);
    Integer createProduct(ProductRequest productRequest);
}
