package it.epicode.be.model;

import javax.persistence.OneToOne;

public class Indirizzo {

	private String via;
	private String civico;
	private String localita;
	private int cap;
	@OneToOne
	private Comune comune;
	
}
