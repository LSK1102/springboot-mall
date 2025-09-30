package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dto.Buyitem;
import com.lsk.springbootmall.dto.CreateOrderRequest;
import com.lsk.springbootmall.dto.OrderQueryParams;
import com.lsk.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userid, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrder(OrderQueryParams orderQueryParams);
}
