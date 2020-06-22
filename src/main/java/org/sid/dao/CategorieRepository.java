package org.sid.dao;

import org.sid.entite.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//tous les methode sont acceble par API
@RepositoryRestResource
@CrossOrigin("*")

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
