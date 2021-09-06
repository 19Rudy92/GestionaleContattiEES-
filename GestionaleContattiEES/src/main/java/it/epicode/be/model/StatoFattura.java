package it.epicode.be.model;

import javax.persistence.OneToMany;

public class StatoFattura {
	
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "fattura")
	private Fattura fattura;
	

}
