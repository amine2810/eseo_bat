package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Admin;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "SELECT  ad  from  Admin ad where ad.nom  LIKE  %?1%  or ad.prenom  LIKE  %?1% ")
    public List<Admin> getAdminByName (String name);

    @Query(value = "SELECT  ad  from  Admin ad where ad.email  LIKE  ?1 ")
    public Admin getAdminByEmail (String email);
}
