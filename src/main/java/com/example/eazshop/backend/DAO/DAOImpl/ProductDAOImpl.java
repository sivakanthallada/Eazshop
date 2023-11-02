package com.example.eazshop.backend.DAO.DAOImpl;


import com.example.eazshop.backend.DAO.ProductDAO;
import com.example.eazshop.backend.model.AllCategory;
import com.example.eazshop.backend.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

//    @Value("${image.upload.directory}") // Configure the directory to store images in application.properties
//    private String imageUploadDirectory;

    @Autowired
    public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        try{
            String query = "SELECT * FROM product WHERE category = ?";
            logger.info("Finding products by category.... {}",category);
            return jdbcTemplate.query(query, this::ProductRowMapper,category);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching product details by {} category: {}", category,e.getMessage());
            return null;
        }
    }
    @Override
    public List<AllCategory> getAllCategories() {
        try{
            String query = "SELECT category,count(productCode) as count FROM product GROUP BY category";
//            logger.info("Fetching all categories");
            return jdbcTemplate.query(query, this::CatRowMapper);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching all categories {}",e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try{
            String query = "SELECT * FROM product";
            logger.info("Finding all product details");
            return jdbcTemplate.query(query, this::ProductRowMapper);
        }
        catch (Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching all product details {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Product getProductById(String productCode) {
        try{

            String query = "SELECT * FROM product WHERE productCode = ?";
//            logger.info("Product details fetching.... for productCode {}",productCode);
            return jdbcTemplate.queryForObject(query, this::ProductRowMapper, productCode);
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while fetching product details for productCode {}: {}", productCode,e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addProduct(Product product) {
        try{
            String query =
                    "INSERT INTO product (productCode, productName, model, category, manufacturer, manufactureDate, expiryDate, price,productDesc,image) " +
                            "SELECT 'PROD' || LPAD(COALESCE(TO_NUMBER(MAX(SUBSTR(productCode, 5))), 0) + 1, 3, '0'),?, ?, ?, ?, ?, ?, ?,?,?" +
                            "FROM product";
            logger.info("Adding new product....");
            jdbcTemplate.update(query, product.getProductName(),
                    product.getModel(), product.getCategory().toLowerCase(), product.getManufacturer(), product.getManufactureDate(),
                    product.getExpiryDate(), product.getPrice(),product.getProductDesc(),product.getImage());
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while adding product details {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyProduct(String productCode,Product product) {
        try{
            String query = "UPDATE product SET  manufacturer=?," +
                    " manufactureDate=?, expiryDate=?, price=?, productDesc=?, image=? WHERE productCode = ?";
            logger.info("Modifying product by productCode {}",productCode);
            jdbcTemplate.update(query, product.getManufacturer(), product.getManufactureDate(),
                    product.getExpiryDate(), product.getPrice(), product.getProductDesc(), product.getImage(), productCode);
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while modifying product details for productCode {}: {}", productCode,e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(String productCode) {
        try{
            String query = "DELETE FROM product WHERE productCode = ?";
            logger.info("Deleting product by productCode.... {}",productCode);
            jdbcTemplate.update(query, productCode);
        }
        catch(Exception e){
            logger.error("An exception occurred at DAOImpl level while deleting product with productCode {}: {}", productCode,e.getMessage());
            return false;
        }
        return true;
    }

    private Product ProductRowMapper(ResultSet resultSet, int rowNum) throws SQLException{
        Product product=new Product();
        product.setProductCode(resultSet.getString("productCode"));
        product.setProductName(resultSet.getString("productName"));
        product.setCategory(resultSet.getString("category"));
        product.setModel(resultSet.getString("model"));
        product.setManufacturer(resultSet.getString("manufacturer"));
        product.setManufactureDate(resultSet.getDate("manufactureDate"));
        product.setExpiryDate(resultSet.getDate("expiryDate"));
        product.setPrice(resultSet.getLong("price"));
        product.setProductDesc(resultSet.getString("productDesc"));
        product.setImage(resultSet.getString("image"));
        return product;
    }
    private AllCategory CatRowMapper(ResultSet resultSet, int rowNum) throws SQLException{
        AllCategory allCategory=new AllCategory();
        allCategory.setCategory(resultSet.getString("category"));
        allCategory.setCount(resultSet.getInt("count"));
        return allCategory;
    }
}
