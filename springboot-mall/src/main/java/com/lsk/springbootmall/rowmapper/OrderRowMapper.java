package com.lsk.springbootmall.rowmapper;

import com.lsk.springbootmall.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet,int i)throws SQLException {
        Order order=new Order();
        order.setOrderId(resultSet.getInt("order_Id"));
        order.setUserId(resultSet.getInt("user_Id"));
        order.setTotalAmount(resultSet.getInt("total_amount"));
        order.setCreatedDate(resultSet.getDate("created_date"));
        order.setLastModifiedDate(resultSet.getDate("last_modified_date"));
        return order;
    }
}
