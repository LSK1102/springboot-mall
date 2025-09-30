package com.lsk.springbootmall.dao;

import com.lsk.springbootmall.dto.CreateOrderRequest;
import com.lsk.springbootmall.model.Order;
import com.lsk.springbootmall.model.Orderitem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userid,Integer totalAmount);
    void createOrderItems(Integer orderId, List<Orderitem> orderitemList);
    Order getOrderById(Integer orderId);
    List<Orderitem> getOrderItemsById(Integer orderId);
}
