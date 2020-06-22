package org.sid.dao.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import java.util.Date;
import  java.util.List;


import org.sid.dao.CategorieRepository;
import org.sid.dao.CinemaRepository;
import org.sid.dao.FilmRepository;
import org.sid.dao.PlaceRepository;
import org.sid.dao.ProjectionFilmRepository;
import org.sid.dao.SalleRepository;
import org.sid.dao.SeanceRepository;
import org.sid.dao.TickeRepository;
import org.sid.dao.VilleRepository;
import org.sid.entite.Categorie;
import org.sid.entite.Cinema;
import org.sid.entite.Film;
import org.sid.entite.Place;
import org.sid.entite.ProjectionFilm;
import org.sid.entite.Salle;
import org.sid.entite.Seance;
import org.sid.entite.Ticket;
import org.sid.entite.Ville;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


//couche metier
@Service
//toutes les methodes sont transtionel
@Transactional
public class CinemaInitServiceImpl implements ICinemaIntService {
// injection des depandances
@Autowired	
	private VilleRepository villeRepository;
@Autowired	
private CinemaRepository cinemaRepository ;
@Autowired	
private SalleRepository salleRepository ;
@Autowired	
private PlaceRepository placeRepository;
@Autowired	
private SeanceRepository seanceRepository ;
@Autowired	
private FilmRepository filmRepository ;
@Autowired	
private ProjectionFilmRepository projectionFilmRepository ;
@Autowired	
private CategorieRepository categorieRepository ;
@Autowired	
private TickeRepository tickeRepository ;
	
	@Override
	public void initVilles() {
		//liste  des villes 
		Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{

			Ville ville =new Ville()	;
			ville.setName(nameVille);
			villeRepository.save(ville);

		
		});
	}
	@Override
	//dans chaque  ville on a  ajouter les cinemas
	public void initCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("Megarama","IMAX","FOUNOUN","CHAHRAZAD","DAOULIZ")
		.forEach(nameCinema->{
			Cinema cinema =new Cinema();
			cinema.setName(nameCinema);
			//entre 3 et 7
			cinema.setNombreSalles(3+(int)(Math.random()*7));
			cinema.setVille(v);
		cinemaRepository.save(cinema);
			
		});
		
	});	
	
		}

	@Override
	//dans chaque cinema on a ajouter les salles
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				//pour chaque num de salle onva crre une salle
				Salle salle =new Salle();
				salle.setName("salle"+(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlaces(15+(int)(Math.random()*20));
				salleRepository.save(salle);
			
			}
		
	});	
		
	}

	@Override
	//dans chaque Salle on va ajouter les Places
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlaces();i++) {
				Place place=new Place()	;
				place.setNumero((i+1));
				place.setSalle(salle);
				placeRepository.save(place);
		}
		
		});
	}

	@Override
	//definir la date de debut
	
	public void initSeances() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
			Seance seance=new Seance();	
			seanceRepository.save(seance);
		try {
			seance.setHeureDebut(dateFormat.parse(s));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		});
		
	}

	@Override
	//definir les categories
	public void initCategories() {
	Stream.of("Histoire","Action","Fiction","Drama").forEach(cat->{
		Categorie categorie=new Categorie();
		categorie.setName(cat);
		categorieRepository.save(categorie);
	});
	
	}

	@Override
	public void initfilms() {
	//definir les Films
		//declaration  dun tableau des durees 
	double[] durees=new double[] {1,1.5,2,2.5,3};
	 List<Categorie> categories=categorieRepository.findAll();
		Stream.of("DENIAL","Harry Potter","JOKER","LOOKING GLASS","THE DEAD DONT DIE").forEach(titreFilm->{
			
			Film film=new Film();
			film.setTitre(titreFilm);
		film.setDuree(durees [new Random().nextInt(durees.length)]);
		//supprimer  l espace dans les titres
			film.setPhoto(titreFilm.replaceAll("", "")+".jpg");
			//choisir une categorie aleratoire pour un film
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});
		
		
		
	}

	@Override
	public void initProjection() {
		double [] prices =new double[] {30,50,60,70,90,100};
		List<Film> films=filmRepository.findAll();
		
	villeRepository.findAll().forEach(ville->{
		ville.getCinemas().forEach(cinema->{
			cinema.getSalles().forEach(salle->{
				//choisir aleratoire un film
			 int index=new Random().nextInt(films.size());
				Film film=films.get(index);
				//filmRepository.findAll().forEach(film->{
				seanceRepository.findAll().forEach(seance->{
					ProjectionFilm projectionFilm=new ProjectionFilm();
					projectionFilm.setDateProjection(new Date());
					projectionFilm.setFilm(film);
					projectionFilm.setPrix(prices[new Random().nextInt(prices.length)]);
					projectionFilm.setSalle(salle);
					projectionFilm.setSeance(seance);
					projectionFilmRepository.save(projectionFilm);
					
						
						
					});				
					
					
				
				});
			});
			
		});
	
	}

		@Override
	public void initTickets() {
		projectionFilmRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjectionFilm(p);
				ticket.setReservee(false);
				
				 tickeRepository.save(ticket);
			});
		});
		
		
		
		}}

