package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dto.Buyitem;
import com.lsk.springbootmall.dto.CreateOrderRequest;
import com.lsk.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userid, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
