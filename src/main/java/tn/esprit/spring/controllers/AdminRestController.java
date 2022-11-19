package tn.esprit.spring.controllers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.entities.Examen;
import tn.esprit.spring.service.IAdminService;
import tn.esprit.spring.service.IJoueurService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    @Autowired
    IAdminService iAdminService;

    @GetMapping("/get-all-admins")
    @ResponseBody
    public List<Admin> getAdmins() {
        return  iAdminService.getAllAdmins() ;
    }

    @GetMapping("/get-admin-byid/{id}")
    @ResponseBody
    public Admin getAdminById(@PathVariable("id") Long id){return iAdminService.getAdminById(id);}

    @GetMapping("/get-admin-byname/{name}")
    @ResponseBody
    public List<Admin> getAdminByName(@PathVariable("name") String name) { return  iAdminService.getAdminByName(name);}

    @GetMapping("/get-admin-byemail/{email}")
    @ResponseBody
    Admin getAdminByEmail(@PathVariable("email") String email) { return iAdminService.getAdminByEmail(email); };
}
