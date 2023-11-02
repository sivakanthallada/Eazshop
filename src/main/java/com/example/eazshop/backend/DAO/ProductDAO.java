package com.example.eazshop.backend.DAO;

import com.example.eazshop.backend.model.Product;
import com.example.eazshop.backend.model.AllCategory;

import java.util.List;

public interface ProductDAO {
    List<Product> findProductByCategory(String category);
    List<Product> getAllProducts();
    Product getProductById(String productCode);
    List<AllCategory> getAllCategories();
    boolean addProduct(Product product);
    boolean modifyProduct(String productCode,Product product);
    boolean deleteProduct(String productCode);
}
