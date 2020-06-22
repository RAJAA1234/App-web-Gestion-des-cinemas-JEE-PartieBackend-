package org.sid.web;


import org.sid.dao.CinemaRepository;
import org.sid.dao.VilleRepository;
import org.sid.entite.Cinema;
import org.sid.entite.Ville;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.util.List;


@Controller
public class cinemaconrolleur {
	


	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;

	@GetMapping(path = "/index")
	public String index()
	{
		return "index"; 
		
	}
	
	
	@GetMapping(path = "/villes")
	public String list(Model model)
	{	
			
		List<Ville>villes=villeRepository.findAll();
		model.addAttribute("villes",villes);
		
		return "villes";
	}

	@GetMapping(path = "/cinemas")
	public String list2(Model model)
	{	
			
		List<Cinema>cinemas=cinemaRepository.findAll();
		model.addAttribute("cinemas",cinemas);
		
		return "cinemas";
	}
	
	
	
	
	
}
