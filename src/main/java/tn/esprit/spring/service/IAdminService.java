package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Admin;

import java.util.List;
@Service
public interface IAdminService {
    List<Admin> getAllAdmins();
    Admin addAdmin(Admin admin);
    void removeAdmin(Long id);
    Admin getAdminById(Long id);
    List<Admin> getAdminByName(String name);
    Admin getAdminByEmail(String email);
    Admin updateAdmin(Admin admin);

}
