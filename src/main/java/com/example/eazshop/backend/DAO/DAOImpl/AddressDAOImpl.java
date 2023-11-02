package com.example.eazshop.backend.DAO.DAOImpl;


import com.example.eazshop.backend.DAO.AddressDAO;
import com.example.eazshop.backend.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);

    @Autowired
    public AddressDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> getAddressByCustomerId(String customerId) {
        try{
            String query = "select * from Address where customerId=?";
            logger.info("Fetching address by customerId.... {}",customerId);
            return jdbcTemplate.query(query, this::addressRowMapper, new Object[]{customerId});
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching addresses for customer ID {}: {}", customerId, e.getMessage());
            return null;
        }
    }

    @Override
    public Address getAddressByAddressId(String addressId) {
        try{
            String query = "select * from Address where addressId=?";
            logger.info("Fetching orders by addressId.... {}",addressId);
            return jdbcTemplate.queryForObject(query, this::addressRowMapper, addressId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching addresses for address ID {}: {}", addressId, e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addAddress(Address address) {
        try{
            String query="INSERT INTO address(addressId,customerId,zipCode,street,city,state,country)" +
                    "SELECT 'ADD' || LPAD(COALESCE(TO_NUMBER(MAX(SUBSTR(addressId, 5))), 0) + 1, 3, '0'),?,?,?,?,?,?" +
                    "FROM Address";
            logger.info("Adding address details....");
            jdbcTemplate.update(query,address.getCustomerId(),address.getZipCode(),address.getStreet(),address.getCity(),address.getState(),address.getCountry());
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while adding addresses for customer ID {}: {}", address.getCustomerId(), e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateAddress(String addressId,Address address) {
        try{
            String query = "update Address set city=?, zipCode=?, state=?, country=?, street=? where addressId=?";
            jdbcTemplate.update(query,address.getCity(),address.getZipCode(),address.getState(),address.getCountry(),address.getStreet(),addressId);
            logger.info("Updating address details for addressId.... {}",addressId);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while updating addresses for customer ID {}: {}", addressId, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAddress(String addressId) {
        try{
            String query = "delete from Address where addressId=?";
            logger.info("Deleting address by addressId.... {}",addressId);
            jdbcTemplate.update(query,addressId);
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting addresses with address ID {}: {}", addressId, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAddressByCustomerId(String customerId) {
        try{
            String query = "delete from Address where customerId=?";
            logger.info("Deleting address by customerId.... {}",customerId);
            jdbcTemplate.update(query,customerId);
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting addresses for customer ID {}: {}", customerId, e.getMessage());
            return false;
        }
        return true;
    }

    private Address addressRowMapper(ResultSet resultSet, int rowNum) throws SQLException {
        Address address = new Address();
        address.setCustomerId(resultSet.getString("customerId"));
        address.setAddressId(resultSet.getString("addressId"));
        address.setZipCode(resultSet.getInt("zipCode"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setState(resultSet.getString("state"));
        address.setCountry(resultSet.getString("country"));
        return address;
    }
}
