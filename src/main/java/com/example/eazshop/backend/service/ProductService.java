package com.example.eazshop.backend.service;


import com.example.eazshop.backend.DAO.DAOImpl.ProductDAOImpl;
import com.example.eazshop.backend.model.AllCategory;
import com.example.eazshop.backend.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductDAOImpl productDAOImpl;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public boolean saveProduct(Product product) {
        return productDAOImpl.addProduct(product);
    }

    public boolean modifyProduct(String productCode,Product product) {
        return productDAOImpl.modifyProduct(productCode,product);
    }

    public List<Product> getAllProductByCategory(String category) {
        List<Product> productList = productDAOImpl.findProductByCategory(category);
        return productList;
    }
    public List<AllCategory> getAllCategory() {
        List<AllCategory> categoryList = productDAOImpl.getAllCategories();
        return categoryList;
    }

    public Product getProductById(String productCode) {
        Product productDetailsByCategory=null;
        try{
            logger.info("Product service layer implementing.... ");
            productDetailsByCategory = productDAOImpl.getProductById(productCode);
        }
        catch(Exception e){
            logger.error("An exception occurred at product service layer {}", e.getMessage());
            return null;
        }
        return productDetailsByCategory;
    }

    public boolean deleteProduct(String productCode) {
        return productDAOImpl.deleteProduct(productCode);
    }

    public List<Product> getAllProducts() {
        List<Product> productList =null;
        try{
            logger.info("Order service layer implementing.... ");
            productList = productDAOImpl.getAllProducts();
        }
        catch(Exception e){
            logger.error("An exception occurred at product service layer {}", e.getMessage());
            return null;
        }
        return productList;
    }
}
