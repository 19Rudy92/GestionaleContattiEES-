package it.epicode.be.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import it.epicode.be.dto.ClienteDTO;
import it.epicode.be.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Page<Cliente> findByRagioneSocialeContainingIgnoreCase(String nome, Pageable p);
	
//	Page<Cliente> findByNomeContainingIgnoreCase(String nomeContatto, Pageable p);
	
	Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(BigDecimal fatturatoAnnuale, Pageable p);
	
	Page<Cliente> findByFatturatoAnnualeLessThanEqual(BigDecimal fatturatoAnnuale, Pageable p);

	Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal fatturatoAnnualeMinimo,
			BigDecimal fatturatoAnnualeMassimo, Pageable p);
	
	Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable p);
	
	Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable p);

}
