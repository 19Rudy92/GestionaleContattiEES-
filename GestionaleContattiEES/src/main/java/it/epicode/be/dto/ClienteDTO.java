package it.epicode.be.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Comune;
import it.epicode.be.model.Fattura;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
	
	private Long id;
	private String nomeCliente;
	private String cognomeCliente;
	private String telefonoCliente;
	private String emailCliente;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private BigDecimal fatturatoAnnuale;
	
	public static ClienteDTO fromCliente(Cliente c) {
		return new ClienteDTO(c.getId(), c.getNomeContatto(), c.getCognomeContatto(), c.getTelefonoContatto(),
		c.getEmailContatto(), c.getDataInserimento(), c.getDataUltimoContatto(), c.getFatturatoAnnuale());
	}
	
	public Cliente toCliente() {
		Cliente c = new Cliente();
		c.setId(id);
		c.setNomeContatto(nomeCliente);
		c.setCognomeContatto(cognomeCliente);
		c.setTelefonoContatto(telefonoCliente);
		c.setEmailContatto(nomeCliente);
		c.setDataInserimento(dataInserimento);
		c.setDataUltimoContatto(dataUltimoContatto);
		c.setFatturatoAnnuale(fatturatoAnnuale);
		return c;
	}
}
