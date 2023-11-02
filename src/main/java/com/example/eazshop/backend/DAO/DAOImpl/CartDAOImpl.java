package com.example.eazshop.backend.DAO.DAOImpl;


import com.example.eazshop.backend.DAO.CartDAO;
import com.example.eazshop.backend.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CartDAOImpl.class);

    @Autowired
    public CartDAOImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Cart> getCartDetailsByCustomerId(String customerId)
    {
        try{
            String query = "select * from cart where customerId=?";
//            logger.info("Fetching cart of customer.... {}",customerId);
            return jdbcTemplate.query(query, this::cartRowMapper, new Object[]{customerId});
        }
        catch(Exception e)
        {
            logger.error("An exception occurred at DAOImpl level while getting cart details of customerId {}: {}", customerId,e.getMessage());
            return null;
        }

    }


    @Override
    public boolean addToCart(Cart cart)
    {
        try{
            String query = "insert into cart(productCode,customerId,image,productName,price) values(?,?,?,?,?)";
            logger.info("Product adding to cart.... {}",cart.getProductCode());
            this.jdbcTemplate.update(query, cart.getProductCode(),cart.getCustomerId(),cart.getImage(),cart.getProductName(),cart.getPrice());
        }
        catch(Exception e)
        {
            logger.error("An exception occurred at DAOImpl level while adding product to cart {}", e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteCartByProductId(String productCode)
    {
        try{
            String query = "delete from Cart where productCode=?";
            logger.info("Deleting product from cart with productCode.... {}",productCode);
            jdbcTemplate.update(query,productCode);
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting product from cart with productCode {}: {}", productCode, e.getMessage());
            return false;
        }
        return true;
    }
    private Cart cartRowMapper(ResultSet resultSet, int rowNum) throws SQLException {
        Cart cart = new Cart();
        cart.setProductCode(resultSet.getString("productCode"));
        cart.setCustomerId(resultSet.getString("customerId"));
        cart.setImage(resultSet.getString("image"));
        cart.setProductName(resultSet.getString("productName"));
        cart.setPrice(resultSet.getLong("price"));
        return cart;
    }

}
