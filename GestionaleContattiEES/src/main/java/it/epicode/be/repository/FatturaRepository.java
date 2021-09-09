package it.epicode.be.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long>{
	
	Page<Fattura> findByClienteId(Long id, Pageable p);
	
	Page<Fattura> findByData(LocalDate data, Pageable p);
	
	Page<Fattura> findByStatoFatturaId(Long id, Pageable p);
	
	Page<Fattura> findByAnno(int anno, Pageable p);

	Page<Fattura> findByImportoBetween(BigDecimal importoMinimo, BigDecimal importoMassimo, Pageable p);

}
