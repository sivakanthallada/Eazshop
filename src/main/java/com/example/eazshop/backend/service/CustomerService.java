package com.example.eazshop.backend.service;

import com.example.eazshop.backend.DAO.DAOImpl.CustomerDAOImpl;
import com.example.eazshop.backend.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAOImpl customerDAOImpl;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public boolean saveCustomer(Customer customer) {
        return customerDAOImpl.addCustomerDetails(customer);
    }

    public boolean modifyCustomer(String customerId,Customer customer) {
         return  customerDAOImpl.updateCustomerDetails(customerId,customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = null;
        try{
            logger.info("Customer service layer implementing.... ");
            customerList = customerDAOImpl.getAllCustomer();
        }
        catch( Exception e){
            logger.error("An exception occurred at customer service layer {}", e.getMessage());
            return null;
        }
        return customerList;
    }

    public Customer getCustomerById(String customerId) {
        Customer customerDetailsById = null;
        try{
            logger.info("Customer service layer implementing.... ");
            customerDetailsById = customerDAOImpl.getCustomerById(customerId);
        }
        catch (Exception e){
            logger.error("An exception occurred at customer service layer {}", e.getMessage());
            return null;
        }
        return customerDetailsById;
    }

    public boolean deleteCustomerDetails(String customerId) {
        return customerDAOImpl.deleteCustomerDetails(customerId);
    }

    public Customer getCustomerByMailId(String mailId) {
        Customer customerDetailsByMailId = null;
        try{
            logger.info("Customer service layer implementing.... ");
            customerDetailsByMailId = customerDAOImpl.getCustomerByMailId(mailId);
        }
        catch (Exception e){
            logger.error("An exception occurred at customer service layer {}", e.getMessage());
            return null;
        }
        return customerDetailsByMailId;
    }

    public boolean checkMail(String mailId) {
        return customerDAOImpl.checkEmail(mailId);
    }
}
