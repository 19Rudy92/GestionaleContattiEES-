package it.epicode.be.dto;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComuneDTO {
	
	private Long id;
	private String nomeComune;
	private String nomeProvincia;
	private Long idProvincia;
	
	
	public static ComuneDTO fromComune(Comune c) {
		return new ComuneDTO(c.getId(), c.getNomeComune(), c.getProvincia().getNomeProvincia(), c.getProvincia().getId());
	}
	
	
	public Comune toComune() {
		Comune c = new Comune();
		c.setId(id);
		c.setNomeComune(nomeComune);
		Provincia p = new Provincia();
		p.setNomeProvincia(nomeProvincia);
		p.setId(idProvincia);
		c.setProvincia(p);
		return c;
	}

}
