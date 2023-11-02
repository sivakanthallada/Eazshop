package com.example.eazshop.backend.DAO.DAOImpl;


import com.example.eazshop.backend.DAO.CouponDAO;
import com.example.eazshop.backend.model.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CouponDAOImpl implements CouponDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CouponDAOImpl.class);

    @Autowired
    public CouponDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        try{
            String query = "select * from Coupon";
            logger.info("Fetching all coupon details.... ");
            return this.jdbcTemplate.query(query, this::couponRowMapper);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching all the available coupons {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Coupon getCouponById(String couponCode) {
        try{
            String query = "select * from Coupon where couponCode=?";
            logger.info("Fetching coupon details by id.... ");
            return this.jdbcTemplate.queryForObject(query, this::couponRowMapper,couponCode);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching coupon by id {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addCoupon(Coupon coupon) {
        try{
            String query = "insert into Coupon(couponCode,discountPercentage,validTill) values(?,?,?)";
            logger.info("Adding a new coupon");
            this.jdbcTemplate.update(query,coupon.getCouponCode(),coupon.getDiscountPercentage(),coupon.getValidTill());
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while adding a new coupon {}", e.getMessage());
            return false;
        }
        return true;
    }

    private Coupon couponRowMapper(ResultSet resultSet, int rowNum) throws SQLException{
        Coupon coupon = new Coupon();
        coupon.setCouponCode(resultSet.getString("couponCode"));
        coupon.setDiscountPercentage(resultSet.getInt("discountPercentage"));
        coupon.setValidTill(resultSet.getDate("validTill"));
        return coupon;
    }
}
