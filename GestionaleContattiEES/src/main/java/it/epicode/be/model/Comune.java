package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Comune {
	
	private Long id;
	private String nomeComune;
	@ManyToOne
	private Provincia provincia;

}
