package com.autoservice.service;

import com.autoservice.entity.Admin;
import com.autoservice.entity.Role;
import com.autoservice.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {
    private boolean isCreated = false;
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void createAdmin() {
        Admin admin = new Admin();
        admin.setUsername("Admin");
        admin.setPassword("Admin");
        admin.setRole(Role.ADMIN);
        save(admin);
        isCreated = true;
    }

    private void save(Admin admin) {
        adminRepository.save(admin);
    }


}
