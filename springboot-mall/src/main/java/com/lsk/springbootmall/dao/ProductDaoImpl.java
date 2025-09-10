package com.lsk.springbootmall.dao;

import com.lsk.springbootmall.constant.ProductCategory;
import com.lsk.springbootmall.dto.ProductRequest;
import com.lsk.springbootmall.model.Product;
import com.lsk.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "select product_id,product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date from product WHERE 1=1";

        Map<String,Object> map = new HashMap<>();

        if(productQueryParams.getCategory()!=null){
            sql=sql+" AND category=:category";
            map.put("category",productQueryParams.getCategory());
        }

        if(productQueryParams.getSearch()!=null){
            sql=sql+" AND product_name LIKE :search";
            map.put("search","%"+productQueryParams.getSearch()+"%");
        }
        sql=sql+" ORDER BY "+productQueryParams.getOrderBy()+" "+productQueryParams.getSort();

        sql=sql+" LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        return productList;
    }
    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id,product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date from product WHERE product_id=:productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        List<Product>productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        if(productList.size()>0){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "insert into product( product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date)value(:productName,:category," +
                ":imageUrl,:price,:stock,:description,:created_date,:last_modified_date)";

        Map<String,Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        Integer productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql ="UPDATE product SET product_name = :productName,category = :category," +
                "image_url=:imageUrl,price=:price,stock=:stock,description=:description," +
                "last_modified_date=:last_modified_date WHERE product_id=:productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("last_modified_date",new Date());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "delete from product where product_id=:productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }

}
