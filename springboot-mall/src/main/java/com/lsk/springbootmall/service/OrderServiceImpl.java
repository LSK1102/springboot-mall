package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dao.OrderDao;
import com.lsk.springbootmall.dao.ProductDao;
import com.lsk.springbootmall.dao.UserDao;
import com.lsk.springbootmall.dto.Buyitem;
import com.lsk.springbootmall.dto.CreateOrderRequest;
import com.lsk.springbootmall.model.Order;
import com.lsk.springbootmall.model.Orderitem;
import com.lsk.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userid, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<Orderitem> orderitemList  = new ArrayList<>();


        for(Buyitem buyitem:createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyitem.getProductId());

            int amount = product.getPrice()*buyitem.getQuantity();
            totalAmount += amount;

            Orderitem orderitem = new Orderitem();
            orderitem.setProduct(buyitem.getProductId());
            orderitem.setQuantity(buyitem.getQuantity());
            orderitem.setAmount(amount);

            orderitemList.add(orderitem);

        }

        Integer orderId = orderDao.createOrder(userid,totalAmount);

        orderDao.createOrderItems(orderId,orderitemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<Orderitem> orderitemList=orderDao.getOrderItemsById(orderId);
        order.setOrderitemList(orderitemList);
        return order;
    }
}
