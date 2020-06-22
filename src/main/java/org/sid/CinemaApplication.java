package org.sid;

import org.sid.dao.service.ICinemaIntService;
import org.sid.entite.Film;
import org.sid.entite.Salle;
import org.sid.entite.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

	
	@Autowired
	private ICinemaIntService cinemaIntService;
	@Autowired
private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Film.class,Salle.class,Ticket.class);
		cinemaIntService.initVilles();
		cinemaIntService.initCinemas();
		cinemaIntService.initSalles();
		cinemaIntService.initPlaces();
		cinemaIntService.initSeances();
		cinemaIntService.initCategories();
		cinemaIntService.initfilms();
		cinemaIntService.initProjection();
		cinemaIntService.initTickets();
		
	}

}
