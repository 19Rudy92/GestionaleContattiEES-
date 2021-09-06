package it.epicode.be.model;

import java.util.List;

import javax.persistence.OneToMany;

public class Provincia {
	
	private int id;
	private String nomeProvincia;
	private String sigla;
	private String regione;
	@OneToMany(mappedBy = "comune")
	private List<Comune> comuni;

}
