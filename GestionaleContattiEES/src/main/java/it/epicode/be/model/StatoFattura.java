package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class StatoFattura {
	
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "fattura")
	private Fattura fattura;
	

}
