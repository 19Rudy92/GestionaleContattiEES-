package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Fattura {
	
	private Long id;
	private Integer anno;
	private LocalDate data;
	private BigDecimal importo;
	private Integer numeroFattura;
	@OneToOne
	private Cliente cliente;
	@ManyToOne
	private StatoFattura stato;
	

}
