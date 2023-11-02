package com.example.eazshop.backend.service;


import com.example.eazshop.backend.DAO.DAOImpl.OrdersDAOImpl;
import com.example.eazshop.backend.model.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersService {
    @Autowired
    private OrdersDAOImpl ordersDAOImpl;

    private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    public boolean createOrder(Orders orders) {
        return ordersDAOImpl.addOrderDetails(orders);
    }

    public List<Orders> getAllOrders() {
        List<Orders> ordersList = null;
        try{
            logger.info("Order service layer implementing.... ");
            ordersList = ordersDAOImpl.getAllOrders();
        }
        catch(Exception e){
            logger.error("An exception occurred at orders service layer {}", e.getMessage());
            return null;
        }
        return ordersList;
    }

    public Orders getOrderDetails(String orderId) {
        Orders orderDetailsById = null;
        try{
            logger.info("Order service layer implementing.... ");
            orderDetailsById = ordersDAOImpl.getOrderDetailsById(orderId);
        }
        catch(Exception e){
            logger.error("An exception occurred at orders service layer {}", e.getMessage());
            return null;
        }
        return orderDetailsById;
    }

    public List<Orders> getOrderDetailsByCustomerId(String customerId) {
        List<Orders> orderDetailsByCustomerId = null;
        try{
            logger.info("Order service layer implementing.... ");
            orderDetailsByCustomerId = ordersDAOImpl.getOrdersByCustomerId(customerId);
        }
        catch(Exception e){
            logger.error("An exception occurred at orders service layer {}", e.getMessage());
            return null;
        }
        return orderDetailsByCustomerId;
    }

    public List<Orders> getOrderDetailsByDate(String date) {
        List<Orders> orderDetailsByDate = null;
        try{
            logger.info("Order service layer implementing.... ");
            orderDetailsByDate = ordersDAOImpl.getOrdersByDate(date);
        }
        catch(Exception e){
            logger.error("An exception occurred at orders service layer {}", e.getMessage());
            return null;
        }
        return orderDetailsByDate;
    }

    public boolean modifyOrder(Orders orders) {
         return  ordersDAOImpl.modifyOrderDetails(orders);
    }

    public boolean deleteOrder(String orderId) {
        return ordersDAOImpl.deleteOrderDetails(orderId);
    }

    public boolean deleteOrderByCustomerId(String customerId) {
        return ordersDAOImpl.deleteOrderByCustomerId(customerId);
    }
}
