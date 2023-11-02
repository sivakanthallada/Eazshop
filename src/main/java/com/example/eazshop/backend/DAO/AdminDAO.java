package com.example.eazshop.backend.DAO;


import com.example.eazshop.backend.model.Admin;

public interface AdminDAO {
    boolean addAdmin(Admin admin);

    Admin getAdminById(String adminId);
}
