package org.sid.web;




import org.springframework.http.MediaType;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.FilmRepository;
import org.sid.dao.TickeRepository;

import org.sid.entite.Film;
import org.sid.entite.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;


@RestController
@CrossOrigin("*")
public class CinemaRestContoller {



	//consulter ou upload l image
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TickeRepository tickeRepository;

	

	//dans le byte il ya des donnes sous fome des images
	@GetMapping(path ="/imageFilm/{id}",produces =MediaType.IMAGE_JPEG_VALUE)
	public byte [] image(@PathVariable (name="id")Long id) throws Exception{
		
		Film f=filmRepository.findById(id).get();
		String photoNmae=f.getPhoto();
		//le contenu de la photo
		File file =new File(System.getProperty("user.home")+"/cinema/images/"+photoNmae);
		Path path=Paths.get(file.toURI());
		//retoune tableau d octets
		return Files.readAllBytes(path);
	}
	
	
	
	//effuctuter le payment 
	//@RequestBody : les donnes de tickets va envoyer au corp de requets se fome json
	@PostMapping("/payerTickets")
	//traitement de couche metier 
	@Transactional
	//user a evoyer les id des tickets qui il veut
public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
		//liste des tickets vendu
		List<Ticket> listTickets=new ArrayList<Ticket>();
	//pour chaque id de ticket 
		ticketForm.getTickets().forEach(idTicket->{
			System.out.println(idTicket);
	Ticket ticket=tickeRepository.findById(idTicket).get();
	//modifier le sinfos sur les tickets 
	ticket.setNomClient(ticketForm.getNomClient());
	ticket.setReservee(true);
	ticket.setCodePayement(ticketForm.getCodePayment());
		tickeRepository.save(ticket);
		//chaque ticket enregister il faut ajouter a la liste
	listTickets.add(ticket);
	
	});
		return listTickets;
}
}


@Data
class TicketForm{
	//resovoir  le nom de client 
	private String nomClient;
	private int codePayment;
	//resovoir une liste des id
private List<Long> tickets=new ArrayList<>();	
}


