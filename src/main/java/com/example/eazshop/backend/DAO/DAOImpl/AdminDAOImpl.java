package com.example.eazshop.backend.DAO.DAOImpl;

import com.example.eazshop.backend.DAO.AdminDAO;
import com.example.eazshop.backend.model.Admin;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class AdminDAOImpl implements AdminDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);

    @Autowired
    public AdminDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Admin getAdminById(String adminId) {
        try{
            String query = "select * from Admin where adminId=?";
            logger.info("Fetching admin details by adminId.... {}",adminId);
            return jdbcTemplate.queryForObject(query, this::adminRowMapper, adminId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching admin details for adminId {}: {}", adminId,e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addAdmin(Admin admin) {
        try{
            String query = "insert into admin(adminId,password) SELECT 'ADM' || LPAD(COALESCE(TO_NUMBER(MAX(SUBSTR(adminId, 5))), 0) + 1, 3, '0'),? from admin";
            logger.info("Adding admin details");
            this.jdbcTemplate.update(query,admin.getPassword());
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while adding new Admin {}", e.getMessage());
            return false;
        }
        return true;
    }

    private Admin adminRowMapper(ResultSet resultSet, int rowNum) throws SQLException {
        Admin admin=new Admin();
        admin.setAdminId(resultSet.getString("adminId"));
        admin.setPassword(resultSet.getString("password"));
        return admin;
    }


}
