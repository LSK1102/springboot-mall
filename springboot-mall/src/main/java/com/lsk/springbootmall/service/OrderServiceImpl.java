package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dao.OrderDao;
import com.lsk.springbootmall.dao.ProductDao;
import com.lsk.springbootmall.dao.UserDao;
import com.lsk.springbootmall.dto.Buyitem;
import com.lsk.springbootmall.dto.CreateOrderRequest;
import com.lsk.springbootmall.model.Order;
import com.lsk.springbootmall.model.Orderitem;
import com.lsk.springbootmall.model.Product;
import com.lsk.springbootmall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userid, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userid);
        if (user == null) {
            log.warn("user{}不存在", userid);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<Orderitem> orderitemList  = new ArrayList<>();


        for(Buyitem buyitem:createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyitem.getProductId());

            if (product == null) {
                log.warn("商品不存在{}", buyitem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock()<buyitem.getQuantity()){
                log.warn("商品{}庫存不足{} {}"
                        ,buyitem.getProductId(), product.getStock(), buyitem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(product.getProductId(),product.getStock()-buyitem.getQuantity());

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
