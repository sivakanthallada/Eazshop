package com.example.eazshop.backend.DAO;

import com.example.eazshop.backend.model.Coupon;

import java.util.List;


public interface CouponDAO {
    List<Coupon> getAllCoupons();
    boolean addCoupon(Coupon coupon);
    Coupon getCouponById(String couponCode);
}
