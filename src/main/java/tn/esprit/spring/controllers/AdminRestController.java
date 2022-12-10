package tn.esprit.spring.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Requests.ChangePWDRequest;
import tn.esprit.spring.Requests.LoginRequest;
import tn.esprit.spring.Requests.ResetPWDRequest;
import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.repository.AdminRepository;
import tn.esprit.spring.sec.MailSenderService;
import tn.esprit.spring.service.GeneratorService;
import tn.esprit.spring.service.IAdminService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminRestController {
    @Qualifier("IAdminService")
    @Autowired
    IAdminService iAdminService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    MailSenderService mailSender;
    @Autowired
    GeneratorService generator;


    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

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
    public  Admin getAdminByEmail(@PathVariable("email") String email) { return iAdminService.getAdminByEmail(email); }


    // http://localhost:8087/SpringMVC/admin/add-admin
    @PostMapping("/add-admin")
    //@ResponseBody
    public String addAdmin (@RequestBody Admin admin)
    {   String msg="";
        Admin userExists = iAdminService.getAdminByEmail(admin.getEmail());
        if (userExists != null) {
            msg="There is already a user registered with the email provided";
        } else {
            Admin a = iAdminService.addAdmin(admin);
            msg="Account created successfully";
        }
        return msg;
    }

    @DeleteMapping("/remove-admin/{admin-id}")
    @ResponseBody
    public void removeAdmin(@PathVariable("admin-id") Long adminid) {
        iAdminService.removeAdmin(adminid);
    }



    // http://localhost:8087/SpringMVC/admin/modify-admin
    @PutMapping("/modify-admin/{id}")
    @ResponseBody
    public Admin updateAdmin(@RequestBody Admin user , @PathVariable("id") long userid) {
        Admin user1 = iAdminService.getAdminById(userid);
        if( user.getEmail().isEmpty())
            user.setEmail(user1.getEmail());
        if( user.getNom().isEmpty())
            user.setNom(user1.getNom());
        if( user.getPrenom().isEmpty())
            user.setPrenom(user1.getPrenom());
        if( user.getEmail().isEmpty())
            user.setEmail(user1.getEmail());

        user.setIdUser(userid);

        return iAdminService.updateAdmin(user);
    }

    @ResponseBody
    @PostMapping("/signin")
    public Admin authenticateUser(@RequestBody LoginRequest loginRequest) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Admin userExists = iAdminService.getAdminByEmail(loginRequest.getUsername());
        if (userExists != null) {
            if (encoder.matches(loginRequest.getPassword(), userExists.getMdp())) {
                return (userExists);
            }
        }
       return new Admin();
    }


    @PutMapping("/modify-pwd-admin/{id}")
    @ResponseBody
    public ResponseEntity<?> updatePWD(@RequestBody ChangePWDRequest changePWDRequest , @PathVariable("id") long userid) {
        Admin user = iAdminService.getAdminById(userid);
        if (user != null) {
            String old_entred=changePWDRequest.getOldPassword();
            String old_db= user.getMdp();
            String entred= changePWDRequest.getNewPassword();
            if (! encoder.matches(old_entred,old_db)){
                return ResponseEntity
                        .badRequest()
                        .body(("Error: Ancien mot de passe est incorrect"));
            } else {
                user.setMdp(bCryptPasswordEncoder.encode(entred));
                iAdminService.addAdmin(user);
                return ResponseEntity.ok().body(("Mot de passe changé avec succés"));
            }
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/reset_password")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> reset_password( @RequestBody ResetPWDRequest resetPWDRequest){
        Admin admin = iAdminService.getAdminByEmail(resetPWDRequest.getUsername());
        if (admin != null)
            return ResponseEntity.notFound().build();

        String pwd=generator.Pwdgenerator();
        admin.setMdp(encoder.encode(pwd));
        mailSender.sendEmail(resetPWDRequest.getUsername(),"Récupérer mot de passe","Bonjour Mme/Mr "+admin.getNom()
                +" \n Vous avez oublié votre mot de passe, la connexion est maintenant disponible avec  le nouveau mot de passe ci-dessous . \n"
                +"\n Vous pouvez le changer pour plus de sécurité. \n"
                +pwd
                +"\n \n Merci");
        iAdminService.addAdmin(admin);
        return ResponseEntity.ok().body(("Mot de passe récupéré avec succés"));
    }
    }
