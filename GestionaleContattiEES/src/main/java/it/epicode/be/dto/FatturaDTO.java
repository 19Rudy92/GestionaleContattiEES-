package it.epicode.be.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Fattura;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FatturaDTO {
	
	private Long id;
	private LocalDate data;
	private BigDecimal importo;
	private Integer numeroFattura;
	private long idCliente;
	private String nomeCliente;
	
	
	public static FatturaDTO fromFattura(Fattura f) {
		return new FatturaDTO(f.getId(), f.getData(), f.getImporto(), f.getNumeroFattura(), f.getCliente().getId(),
				f.getCliente().getNomeContatto());
	}
	
	public Fattura toFattura() {
		Fattura f = new Fattura();
		f.setId(id);
		f.setData(data);
		f.setImporto(importo);
		f.setNumeroFattura(numeroFattura);
		Cliente c = new Cliente();
		c.setId(idCliente);
		c.setNomeContatto(nomeCliente);
		f.setCliente(c);
		return f;
	}
	
	
	
	
	
	
	
	
}









