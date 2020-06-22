package org.sid.entite;

import java.util.Collection;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString 

public class Film {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private double duree;
	private String realisateur;
	private String Description;
	private String photo;
	private Date dateSortie;	
	@OneToMany(mappedBy = "film")
	
//dans la consultation il va  pas donne la liste des projections
@JsonProperty( access = Access.WRITE_ONLY)
	private Collection <ProjectionFilm> pojectionsFilms;
	@ManyToOne
	private Categorie categorie;

}
