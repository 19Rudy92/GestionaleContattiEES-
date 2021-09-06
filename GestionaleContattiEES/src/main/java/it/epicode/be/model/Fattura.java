package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.OneToOne;

public class Fattura {
	
	private int id;
	private Integer anno;
	private LocalDate data;
	private BigDecimal importo;
	private Integer numeroFattura;
	@OneToOne
	private Cliente cliente;
	

}
