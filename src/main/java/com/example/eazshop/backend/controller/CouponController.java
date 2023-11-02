package com.example.eazshop.backend.controller;


import com.example.eazshop.backend.model.Coupon;
import com.example.eazshop.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/couponAPI")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/getAllCoupons")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> couponList = null;
        couponList = couponService.getAllCoupons();
        if(couponList==null){
            return new ResponseEntity<List<Coupon>>(couponList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<List<Coupon>>(couponList,HttpStatus.OK);
        }
    }

    @GetMapping("/getCouponById/{couponCode}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable("couponCode") String couponCode){
        Coupon coupon = null;
        coupon = couponService.getCouponById(couponCode);
        if(coupon==null){
            return new ResponseEntity<Coupon>(coupon, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<Coupon>(coupon,HttpStatus.OK);
        }
    }

    @PostMapping("/addCoupon")
    public ResponseEntity<String> addCoupon(@RequestBody Coupon coupon){
        if(couponService.addCoupon(coupon)){
            return new ResponseEntity<String>("Coupon details saved successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while adding coupon details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
