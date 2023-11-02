package com.example.eazshop.backend.service;


import com.example.eazshop.backend.DAO.DAOImpl.AddressDAOImpl;
import com.example.eazshop.backend.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressService {

    @Autowired
    private AddressDAOImpl addressDAOImpl;

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public boolean addAddress(Address address) {
        return addressDAOImpl.addAddress(address);
    }

    public boolean updateAddress(String addressId,Address address) {
        return addressDAOImpl.updateAddress(addressId,address);
    }

    public List<Address> getAddressByCustomerId(String customerId) {
        List<Address> addressList = null;
        try{
            logger.info("Address service layer implementing.... ");
            addressList = addressDAOImpl.getAddressByCustomerId(customerId);
        }
        catch( Exception e){
            logger.error("An exception occurred at address service layer {}", e.getMessage());
            return null;
        }
        return addressList;
    }

    public Address getAddressByAddressId(String addressId) {
        Address address = null;
        try{
            address=addressDAOImpl.getAddressByAddressId(addressId);
            logger.info("Address service layer implementing.... ");
        }
        catch (Exception e){
            logger.error("An exception occurred at address service layer {}", e.getMessage());
            return null;
        }
        return address;
    }

    public boolean deleteAddressByCustomerId(String customerId) {
        return addressDAOImpl.deleteAddressByCustomerId(customerId);
    }

    public boolean deleteAddressByAddressId(String addressId) {
        return addressDAOImpl.deleteAddress(addressId);
    }


}
