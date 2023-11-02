package com.example.eazshop.backend.service;


import com.example.eazshop.backend.DAO.DAOImpl.CouponDAOImpl;
import com.example.eazshop.backend.model.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponService {
    @Autowired
    private CouponDAOImpl couponDAOImpl;

    private static final Logger logger = LoggerFactory.getLogger(CouponService.class);

    public List<Coupon> getAllCoupons() {
        List<Coupon> couponList=null;
        try{
            logger.info("Coupon service layer implementing.... ");
            couponList = couponDAOImpl.getAllCoupons();
        }
         catch (Exception e){
             logger.error("An exception occurred at coupon service layer {}", e.getMessage());
            return null;
         }
        return couponList;
    }

    public Coupon getCouponById(String couponCode) {
        Coupon coupon=null;
        try{
            logger.info("Coupon service layer implementing.... ");
            coupon = couponDAOImpl.getCouponById(couponCode);
        }
        catch (Exception e){
            logger.error("An exception occurred at coupon service layer {}", e.getMessage());
            return null;
        }
        return coupon;
    }

    public boolean addCoupon(Coupon coupon) {
        return couponDAOImpl.addCoupon(coupon);
    }
}
