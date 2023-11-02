package com.example.eazshop.backend.service;


import com.example.eazshop.backend.DAO.DAOImpl.AdminDAOImpl;
import com.example.eazshop.backend.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminService {
    @Autowired
    private AdminDAOImpl adminDAOImpl;

    public boolean addAdmin(Admin admin) {
        return adminDAOImpl.addAdmin(admin);
    }

    public Admin getAdminById(String adminId) {
        return adminDAOImpl.getAdminById(adminId);
    }
}
