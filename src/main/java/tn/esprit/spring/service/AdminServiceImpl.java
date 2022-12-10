package tn.esprit.spring.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.repository.AdminRepository;

import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl  implements IAdminService {

    @Autowired
    AdminRepository adminRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();



    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        admin.setMdp(bCryptPasswordEncoder.encode(admin.getMdp()));
        return adminRepository.save(admin);
    }

    @Override
    public void removeAdmin(Long id) {
        adminRepository.deleteById(id);}

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> getAdminByName(String name) {
        return adminRepository.getAdminByName(name);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.getAdminByEmail(email);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

}
