package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Provincia {
	
	private Long id;
	private String nomeProvincia;
	private String sigla;
	private String regione;
	@OneToMany(mappedBy = "comune")
	private List<Comune> comuni;

}
