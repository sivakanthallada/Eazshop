package com.example.eazshop.backend.DAO;

import com.example.eazshop.backend.model.Orders;

import java.util.List;


public interface OrdersDAO{
    List<Orders> getOrdersByCustomerId(String customerId);
    List<Orders> getOrdersByDate(String date);
    List<Orders> getAllOrders();
    Orders getOrderDetailsById(String orderId);
    boolean addOrderDetails(Orders orders);
    boolean modifyOrderDetails(Orders orders);
    boolean deleteOrderDetails(String orderId);
    boolean deleteOrderByCustomerId(String customerId);
}
