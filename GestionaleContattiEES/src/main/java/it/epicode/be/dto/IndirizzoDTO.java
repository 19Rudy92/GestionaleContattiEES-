package it.epicode.be.dto;

import javax.persistence.OneToOne;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Indirizzo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndirizzoDTO {
	
	private Long id;
	private String via;
	private String civico;
	private String localita;
	private int cap;
	private long idComune;
	private String nomeComune;
	
	
	public static IndirizzoDTO fromIndirizzo(Indirizzo i) {
		return new IndirizzoDTO(i.getId(), i.getVia(), i.getCivico(), i.getLocalita(), i.getCap(), i.getComune().getId(), 
				i.getComune().getNomeComune());
	}
	
	
	public Indirizzo toIndirizzo() {
		Indirizzo i = new Indirizzo();
		i.setId(id);
		i.setVia(via);
		i.setCivico(civico);
		i.setLocalita(localita);
		i.setCap(cap);
		Comune c = new Comune();
		c.setId(id);
		c.setNomeComune(nomeComune);
		i.setComune(c);
		return i;
	}

}










