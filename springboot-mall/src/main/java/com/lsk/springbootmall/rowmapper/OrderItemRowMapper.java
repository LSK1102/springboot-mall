package com.lsk.springbootmall.rowmapper;

import com.lsk.springbootmall.model.Orderitem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<Orderitem> {
    public Orderitem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orderitem orderitem=new Orderitem();
        orderitem.setOrderItemId(rs.getInt("order_item_id"));
        orderitem.setOrderId(rs.getInt("order_id"));
        orderitem.setProduct(rs.getInt("product_id"));
        orderitem.setQuantity(rs.getInt("quantity"));
        orderitem.setAmount(rs.getInt("amount"));

        orderitem.setProductName(rs.getString("product_name"));
        orderitem.setImageUrl(rs.getString("image_url"));
        return orderitem;
    }

}
