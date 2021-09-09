package it.epicode.be.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Page<Cliente> findByRagioneSocialeContainingIgnoreCase(String nome, Pageable p);
	
//	Page<Cliente> findByNomeContainingIgnoreCase(String nomeContatto, Pageable p);
	
	Page<Cliente> findByFatturatoAnnuale(BigDecimal fatturatoAnnuale, Pageable p);

}
