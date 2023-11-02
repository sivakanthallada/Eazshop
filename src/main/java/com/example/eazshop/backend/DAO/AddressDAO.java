package com.example.eazshop.backend.DAO;



import com.example.eazshop.backend.model.Address;

import java.util.List;


public interface AddressDAO {
    List<Address> getAddressByCustomerId(String customerId);
    Address getAddressByAddressId(String addressId);
    boolean addAddress(Address address);
    boolean updateAddress(String addressId,Address address);
    boolean deleteAddress(String addressId);
    boolean deleteAddressByCustomerId(String customerId);
}
