package com.example.eazshop.backend.controller;


import com.example.eazshop.backend.model.Address;
import com.example.eazshop.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/addressAPI")

public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAddressByCustomerId/{customerId}")
    public ResponseEntity<List<Address>> getAddressByCustomerId(@PathVariable("customerId") String customerId){
        List<Address> addressList = addressService.getAddressByCustomerId(customerId);
        if(addressList==null){
            return new ResponseEntity<List<Address>>(addressList,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<List<Address>>(addressList,HttpStatus.OK);
        }
    }

    @GetMapping("/getAddressByAddressId/{addressId}")
    public ResponseEntity<Address> getAddressByAddressId(@PathVariable("addressId") String addressId){
        Address address = addressService.getAddressByAddressId(addressId);
        if(address==null){
            return new ResponseEntity<Address>(address,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<Address>(address,HttpStatus.OK);
        }
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody Address address){
        if(addressService.addAddress(address)){
            return new ResponseEntity<String>("Address details saved successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while adding address details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAddress/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable ("addressId") String addressId,@RequestBody Address address){
        if(addressService.updateAddress(addressId,address)){
            return new ResponseEntity<String>("Address details updated successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while updating address details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAddressByCustomerId/{customerId}")
    public ResponseEntity<String> deleteAddressByCustomerId(@PathVariable("customerId") String customerId){
        if(addressService.deleteAddressByCustomerId(customerId)){
            return new ResponseEntity<String>("Address details deleted with CustId successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error occurred while deleting address details with CustId",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("deleteAddressByAddressId/{addressId}")
    public ResponseEntity<String> deleteAddressByAddressId(@PathVariable("addressId") String addressId){
        if(addressService.deleteAddressByAddressId(addressId)){
            return new ResponseEntity<String>("Address details deleted with addressId successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error occurred while deleting address details with addressId",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
