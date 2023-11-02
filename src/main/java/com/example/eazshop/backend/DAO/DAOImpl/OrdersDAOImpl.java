package com.example.eazshop.backend.DAO.DAOImpl;


import com.example.eazshop.backend.DAO.OrdersDAO;
import com.example.eazshop.backend.model.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class OrdersDAOImpl implements OrdersDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(OrdersDAOImpl.class);

    @Autowired
    public OrdersDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Orders> getOrdersByCustomerId(String customerId) {
        try{
            String query = "SELECT * FROM orders WHERE customerId = ?";
            logger.info("Fetching orders by customerId.... {}",customerId);
            return jdbcTemplate.query(query, this::OrdersRowMapper, new Object[]{customerId});
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching order details for customerId {}: {}", customerId,e.getMessage());
            return null;
        }
    }
    @Override
    public List<Orders> getOrdersByDate(String date) {
        try{
            String query = "SELECT * FROM orders WHERE orderDate = ?";
            logger.info("Fetching orders by date.... {}",date);
            return jdbcTemplate.query(query, this::OrdersRowMapper, new Object[]{date});
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching order details on date {}: {}", date,e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orders> getAllOrders() {
        try{
            String query = "SELECT * FROM orders";
            logger.info("Fetching all orders details");
            return jdbcTemplate.query(query, this::OrdersRowMapper);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching all order details {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Orders getOrderDetailsById(String orderId) {
        try{
            String query = "SELECT * FROM orders WHERE orderId = ?";
            logger.info("Fetching orders by orderId.... {}",orderId);
            return jdbcTemplate.queryForObject(query, this::OrdersRowMapper, orderId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching order details for orderId {}: {}", orderId,e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addOrderDetails(Orders orders) {
        try {
            String query = "INSERT INTO orders (orderId, productCode, customerId, couponCode, quantity, addressId) " +
                    "SELECT 'ORD' || LPAD(COALESCE(TO_NUMBER(MAX(SUBSTR(orderId, 5))), 0) + 1, 3, '0'),?, ?, ?, ?,?" +
                    "FROM orders";
            logger.info("Adding order details....");
            jdbcTemplate.update(query, orders.getProductCode(),
                    orders.getCustomerId(), orders.getCouponCode(), orders.getQuantity(),orders.getAddressId());
            String query2= "update orders set amount=(select price from product where productCode=orders.productCode )*quantity";
            jdbcTemplate.update(query2);
            String query4= "update orders set discountedAmount=amount";
            jdbcTemplate.update(query4);

            String query3="UPDATE orders SET discountedAmount = amount - (SELECT discountPercentage FROM coupon WHERE couponCode = orders.couponCode) * amount * 0.01 WHERE orders.orderId IN (SELECT orders.orderId FROM orders JOIN coupon ON coupon.couponCode = orders.couponCode WHERE coupon.validTill >= SYSDATE)";
            jdbcTemplate.update(query3);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while adding order details {}",e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyOrderDetails(Orders orders) {
        try {
            String query = "UPDATE orders SET productCode = ?, customerId = ?, amount=?, quantity = ?, couponCode=?, addressId=? " +
                    "WHERE orderId = ?";
            logger.info("Modifying order details by orderId.... {}",orders.getOrderId());
            jdbcTemplate.update(query, orders.getProductCode(), orders.getCustomerId(), orders.getAmount(),orders.getQuantity(),orders.getCouponCode(),orders.getAddressId(), orders.getOrderId() );
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while modifying order details {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOrderDetails(String orderId) {
        try {
            String query = "DELETE FROM orders WHERE orderId = ?";
            logger.info("Deleting order by orderId.... {}",orderId);
            jdbcTemplate.update(query, orderId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting order for orderId {}: {}", orderId,e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOrderByCustomerId(String customerId) {
        try {
            String query = "DELETE FROM orders WHERE customerId = ?";
            logger.info("Deleting orders by customerId.... {}",customerId);
            jdbcTemplate.update(query, customerId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting order for customerId {}: {}", customerId,e.getMessage());
            return false;
        }
        return true;
    }

    private Orders OrdersRowMapper(ResultSet resultSet, int rowNum) throws SQLException{
        Orders orders = new Orders();
        orders.setOrderId(resultSet.getString("orderId"));
        orders.setCustomerId(resultSet.getString("customerId"));
        orders.setProductCode(resultSet.getString("productCode"));
        orders.setAmount(resultSet.getDouble("amount"));
        orders.setCouponCode(resultSet.getString("couponCode"));
        orders.setQuantity(resultSet.getInt("quantity"));
        orders.setOrderDate(resultSet.getDate("orderDate"));
        orders.setAddressId(resultSet.getString("addressId"));
        orders.setDiscountedAmount(resultSet.getDouble("discountedAmount"));
        return orders;
    }
}
