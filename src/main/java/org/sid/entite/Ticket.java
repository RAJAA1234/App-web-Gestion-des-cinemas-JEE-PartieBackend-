package org.sid.entite;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString

public class Ticket {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nomClient;
private double prix;
//accepter les valeurs null
@Column(unique =false,nullable =true)
private Integer codePayement;
private boolean reservee ;
@ManyToOne
private ProjectionFilm projectionFilm;
@ManyToOne 
private Place place;


}
