package it.epicode.be.model;

import javax.persistence.ManyToOne;

public class Comune {
	
	private int id;
	private String nomeComune;
	@ManyToOne
	private Provincia provincia;

}
