package it.epicode.be.dto;

import it.epicode.be.model.Provincia;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProvinciaDTO {
	
	private Long id;
	private String nomeProvincia;
	private String sigla;
	private String regione;
	
	
	public static ProvinciaDTO fromProvincia(Provincia p) {
		return new ProvinciaDTO(p.getId(), p.getNomeProvincia(), p.getSigla(), p.getRegione());
	}
	
	public Provincia toProvincia() {
		Provincia p = new Provincia();
		p.setId(id);
		p.setNomeProvincia(nomeProvincia);
		p.setSigla(sigla);
		p.setRegione(regione);
		return p;
	}

	
	
	
	
}

















