package com.example.eazshop.backend.DAO;



import com.example.eazshop.backend.model.Cart;

import java.util.List;

public interface CartDAO {
    boolean addToCart(Cart cart);
    List<Cart> getCartDetailsByCustomerId(String customerId);
    boolean deleteCartByProductId(String productCode);
}
