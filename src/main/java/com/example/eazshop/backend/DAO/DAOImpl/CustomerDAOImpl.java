package com.example.eazshop.backend.DAO.DAOImpl;


import com.example.eazshop.backend.DAO.CustomerDAO;
import com.example.eazshop.backend.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private AddressDAOImpl addressDAOImpl;

    @Autowired
    private OrdersDAOImpl ordersDAOImpl;

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Autowired
    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomer(){
        try{
            String query = "select * from Customer";
            logger.info("Fetching all customer details");
            return jdbcTemplate.query(query, this::customerRowMapper);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching all the available customers {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerById(String customerId) {
        try{
            String query = "select * from Customer where customerId=?";
            logger.info("Fetching customer details by customerId.... {}",customerId);
            return jdbcTemplate.queryForObject(query, this::customerRowMapper, customerId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching customer details for customerId {}: {}", customerId,e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerByMailId(String mailId) {
        try{
            String query = "select * from Customer where mailId=?";
            logger.info("Fetching customer by emailId.... {}",mailId);
            return jdbcTemplate.queryForObject(query, this::customerRowMapper, mailId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching customer details for mailId {}: {}", mailId,e.getMessage());
            return null;
        }
    }

    @Override
    public boolean checkEmail(String mailId) {
        try{
            String query="select * from Customer where mailId=?";
            logger.info("Authenticating emailId {}",mailId);
            Customer customer = jdbcTemplate.queryForObject(query, this::customerRowMapper, mailId);
            if(customer==null){
                return false;
            }
            else{
                return true;
            }
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while checking email presence {}: {}", mailId,e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addCustomerDetails(Customer customer) {
        try{
            String query="INSERT INTO customer(customerId,customerName,contact,mailId,password)" +
                    "SELECT 'CUST' || LPAD(COALESCE(TO_NUMBER(MAX(SUBSTR(customerId, 5))), 0) + 1, 3, '0'),?,?,?,?" +
                    "FROM Customer";
            logger.info("Adding customer Details.... ");
            jdbcTemplate.update(query,customer.getCustomerName(),customer.getContact(),customer.getMailId(),customer.getPassword());
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while adding customer details {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCustomerDetails(String customerId,Customer customer) {
        try {
            String query="update customer set customerName=?, contact=? where customerId=?";
            logger.info("Updating customer details for customerId.... {}",customerId);
            jdbcTemplate.update(query,customer.getCustomerName(),customer.getContact(),customerId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while updating customer details for customerId {}: {}", customerId,e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCustomerDetails(String customerId) {
        try{
            String query = "delete from Customer where customerId=?";
            logger.info("Deleting customer details by customerId.... {}",customerId);
            jdbcTemplate.update(query,customerId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting customer details for customerId {}: {}", customerId,e.getMessage());
            return false;
        }
        return true;
    }

    private Customer customerRowMapper(ResultSet resultSet, int rowNum) throws SQLException{
        Customer customer=new Customer();
        customer.setCustomerId(resultSet.getString("customerId"));
        customer.setCustomerName(resultSet.getString("customerName"));
        customer.setContact(resultSet.getLong("contact"));
        customer.setMailId(resultSet.getString("mailId"));
        customer.setPassword(resultSet.getString("password"));
        return customer;
    }
}
