package com.example.eazshop.backend.controller;


import com.example.eazshop.backend.model.Customer;
import com.example.eazshop.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customerAPI")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList = null;
        customerList = customerService.getAllCustomers();
        if(customerList==null){
            return new ResponseEntity<List<Customer>>(customerList,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
        }
    }

    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") String customerId){
        Customer customerDetailsById = null;
        customerDetailsById = customerService.getCustomerById(customerId);
        if(customerDetailsById == null){
            return new ResponseEntity<Customer>(customerDetailsById,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<Customer>(customerDetailsById,HttpStatus.OK);
        }
    }

    @GetMapping("/getCustomerByMailId/{mailId}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("mailId") String mailId){
        Customer customerDetailsByMailId = null;
        customerDetailsByMailId = customerService.getCustomerByMailId(mailId);
        if(customerDetailsByMailId == null){
            return new ResponseEntity<Customer>(customerDetailsByMailId,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<Customer>(customerDetailsByMailId,HttpStatus.OK);
        }
    }

    @GetMapping("/checkEmail/{mailId}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable("mailId") String mailId){
        if(customerService.checkMail(mailId)){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
        }
    }

    @PostMapping("/saveCustomerDetails")
    public ResponseEntity<String> saveCustomerDetails(@RequestBody Customer customer){
        if(customerService.saveCustomer(customer)) {
            return new ResponseEntity<String>("Customer details saved successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while adding customer details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modifyCustomerDetails/{customerId}")
    public ResponseEntity<String> modifyCustomerDetails(@PathVariable("customerId") String customerId,@RequestBody Customer customer){
        if(customerService.modifyCustomer(customerId,customer)){
            return new ResponseEntity<String>("Customer details modified successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error occurred while modifying customer details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCustomerDetails/{customerId}")
    public ResponseEntity<String> deleteCustomerDetails(@PathVariable("customerId") String customerId){
        if(customerService.deleteCustomerDetails(customerId)){
            return new ResponseEntity<String>("Customer details deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error occurred while deleting customer details",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
