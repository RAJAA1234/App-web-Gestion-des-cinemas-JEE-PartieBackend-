package org.sid.entite;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1",types= {org.sid.entite.ProjectionFilm.class})

public interface ProjectionProj {
	public Long getId();
	public double getprix();
	public Date getdateProjection();
	public Salle getSalle();
	public Film getFilm();
	public Seance getSeance();
	public Collection<Ticket>getTickets();	
}