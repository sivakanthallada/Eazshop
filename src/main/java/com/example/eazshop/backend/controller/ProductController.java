package com.example.eazshop.backend.controller;


import com.example.eazshop.backend.model.AllCategory;
import com.example.eazshop.backend.model.Product;
import com.example.eazshop.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/productAPI")
public class ProductController {
//    @Value("${image.upload.directory}") // Configure the directory to store images in application.properties
//    private String imageUploadDirectory;
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProductByCategory/{category}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("category") String category){
        List<Product> productList = productService.getAllProductByCategory(category);
        if(productList!=null){
            return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Product>>(productList,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<AllCategory>> getAllCategories(){
        List<AllCategory> categorylist = productService.getAllCategory();
        if(categorylist!=null){
            return new ResponseEntity<List<AllCategory>>(categorylist,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<AllCategory>>(categorylist,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProductById/{productCode}")
    public ResponseEntity<Product> getProductById(@PathVariable("productCode") String productCode){
        Product productDetail = productService.getProductById(productCode);
        if(productDetail!=null){
            return new ResponseEntity<Product>(productDetail,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Product>(productDetail,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        if(productList!=null){
            return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Product>>(productList,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveProductDetails")
//    public ResponseEntity<String> saveProductDetails(@RequestParam("img") MultipartFile imageFile, @ModelAttribute Product product) {
//        if (!imageFile.isEmpty()) {
//            try {
//                String originalFileName = imageFile.getOriginalFilename();
//                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//                String newFileName = generateUniqueFileName(fileExtension);
//
//                Path imageFilePath = Paths.get(imageUploadDirectory, newFileName);
//
//                // Save the image file to the server directory
//                Files.copy(imageFile.getInputStream(), imageFilePath, StandardCopyOption.REPLACE_EXISTING);
//
//                // Set the image file path on the product object
//                product.setImage(imageFilePath.toString());
//
//                // Call the ProductService to save the product
//                productService.saveProduct(product);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return new ResponseEntity<>("Error in saving the image file", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return new ResponseEntity<>("Product details saved successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("No image file provided", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    private String generateUniqueFileName(String fileExtension) {
//        return "image_" + System.currentTimeMillis() + fileExtension;
//    }
//        product.setImage(imageFile);
//
//        if (productService.saveProduct(product)) {
//            return new ResponseEntity<>("Product details saved successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Error in saving Product details", HttpStatus.INTERNAL_SERVER_ERROR);
//        }


    public ResponseEntity<String> saveProductDetails(@RequestBody Product product){
        System.out.println(product.getImage());
        if(productService.saveProduct(product)){
            return new ResponseEntity<String>("Product details saved successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error in saving Product details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    static class ImagePreviewRequest {
        private String previewUrl;

        public String getPreviewUrl() {
            return previewUrl;
        }

        public void setPreviewUrl(String previewUrl) {
            this.previewUrl = previewUrl;
        }
    }

    @PutMapping("/modifyProductDetails/{productCode}")
    public ResponseEntity<String> modifyProductDetails(@PathVariable("productCode") String productCode,@RequestBody Product product){
        if(productService.modifyProduct(productCode,product)){
            return new ResponseEntity<String>("Product details modified successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error in modifying Product details", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteProduct/{productCode}")
    public ResponseEntity<String> deleteProductDetails(@PathVariable("productCode") String productCode){
        if(productService.deleteProduct(productCode)){
            return new ResponseEntity<String>("Product details deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error in deleting Product details", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
