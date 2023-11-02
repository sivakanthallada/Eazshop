package com.example.eazshop.backend.controller;



import com.example.eazshop.backend.model.Admin;
import com.example.eazshop.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/adminAPI")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin){
        if(adminService.addAdmin(admin)){
            return new ResponseEntity<String>("Admin details saved successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred while adding admin details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAdminById/{adminId}")
    public ResponseEntity<Admin> getCustomerById(@PathVariable("adminId") String adminId){
        Admin adminDetailsById = null;
        adminDetailsById = adminService.getAdminById(adminId);
        if(adminDetailsById == null){
            return new ResponseEntity<Admin>(adminDetailsById,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<Admin>(adminDetailsById,HttpStatus.OK);
        }
    }
}
