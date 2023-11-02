package com.example.eazshop.backend.DAO;



import com.example.eazshop.backend.model.Customer;

import java.util.List;


public interface CustomerDAO{
    List<Customer> getAllCustomer();
    Customer getCustomerById(String customerId);
    boolean addCustomerDetails(Customer customer);
    boolean updateCustomerDetails(String customerId,Customer customer);
    boolean deleteCustomerDetails(String customerId);

    Customer getCustomerByMailId(String mailId);

    boolean checkEmail(String mailId);
}
