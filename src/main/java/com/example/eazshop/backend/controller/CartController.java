package com.example.eazshop.backend.controller;


import com.example.eazshop.backend.model.Cart;
import com.example.eazshop.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cartAPI")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/getCartDetailsByCustomerId/{customerId}")
    public ResponseEntity<List<Cart>> getAllCoupons(@PathVariable("customerId") String customerId){
        List<Cart> cartList = cartService.getCartDetailsByCustomerId(customerId);
        if(cartList==null){

            return new ResponseEntity<List<Cart>>(cartList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<List<Cart>>(cartList,HttpStatus.OK);
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart){
        if(cartService.addToCart(cart)){
            return new ResponseEntity<String>("Cart details saved successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while adding cart details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCartByProductId/{productCode}")
    public ResponseEntity<String> deleteCartByProductId(@PathVariable("productCode") String productCode){
        if(cartService.deleteCartByProductId(productCode)){
            return new ResponseEntity<String>("Cart details deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while deleting cart details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
