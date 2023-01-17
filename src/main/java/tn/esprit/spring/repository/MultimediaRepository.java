package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Multimedia;

@Repository
public interface MultimediaRepository  extends CrudRepository<Multimedia,Long> {

}
