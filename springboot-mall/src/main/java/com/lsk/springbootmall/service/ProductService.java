package com.lsk.springbootmall.service;
import com.lsk.springbootmall.dao.ProductQueryParams;
import com.lsk.springbootmall.dto.ProductRequest;
import com.lsk.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productid);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);

}
