package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Indirizzo {

	private String via;
	private String civico;
	private String localita;
	private int cap;
	@OneToOne
	private Comune comune;
	
}
