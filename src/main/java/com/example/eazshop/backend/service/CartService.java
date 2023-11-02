package com.example.eazshop.backend.service;


import com.example.eazshop.backend.DAO.DAOImpl.CartDAOImpl;
import com.example.eazshop.backend.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CartService {
    @Autowired
    private CartDAOImpl cartDAOImpl;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public List<Cart> getCartDetailsByCustomerId(String customerId) {
        List<Cart> cartList=null;
        try{
            logger.info("Cart service layer implementing.... ");
            cartList = cartDAOImpl.getCartDetailsByCustomerId(customerId);
        }
        catch (Exception e){
            logger.error("An exception occurred at cart service layer {}", e.getMessage());
            return null;
        }
        return cartList;
    }

    public boolean addToCart(Cart cart) {
        return cartDAOImpl.addToCart(cart);
    }
    public boolean deleteCartByProductId(String productCode) {return cartDAOImpl.deleteCartByProductId(productCode);}
}
