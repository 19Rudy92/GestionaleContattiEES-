package it.epicode.be.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Fattura;
import it.epicode.be.repository.FatturaRepository;

@Service
public class FatturaService {
	
	@Autowired
	private FatturaRepository fatturaRepo;
	
	public Page<Fattura> getAllFatture(Pageable p){
		return fatturaRepo.findAll(p);
	}
	
	public Fattura aggiungiFattura(Fattura fattura) {
		return fatturaRepo.save(fattura);
	}
	
	public Optional<Fattura> getFatturaById(Long id){
		Optional<Fattura> trovata = fatturaRepo.findById(id);
		return trovata;
	}
	
	public void deleteFatturaById(Long id) {
		Optional<Fattura> fattura = fatturaRepo.findById(id);
		fatturaRepo.deleteById(id);
	}
	
	public void updateFattura(Fattura fattura) {
		Optional<Fattura> of  = fatturaRepo.findById(fattura.getId());
		if(of.isEmpty()) {
			throw new EntityNotFoundException("La fattura con id " + fattura.getId() + " non esiste");
		}
		else {
			fatturaRepo.save(fattura);
		}
	}
	
	public Page<Fattura> getFattureByCliente(Long id, Pageable p){
		Page<Fattura> trovata = fatturaRepo.findByClienteId(id, p);
		return trovata;
	}
	
	public Page<Fattura> getFatturaByData(LocalDate date, Pageable p){
		Page<Fattura> trovata = fatturaRepo.findByData(date, p);
		return trovata;
	}
	
	public Page<Fattura> getFattureByIdStatoFattura(Long id, Pageable p){
		Page<Fattura> trovata = fatturaRepo.findByStatoFatturaId(id, p);
		return trovata;
	}
	
	public Page<Fattura> getFattureByAnno(int anno, Pageable p){
		Page<Fattura> trovata = fatturaRepo.findByAnno(anno, p);
		return trovata;
	}

	public Page<Fattura> findByRangeImportiBetween(BigDecimal importoMinimo, BigDecimal importoMassimo, Pageable p) {
		return fatturaRepo.findByImportoBetween(importoMinimo, importoMassimo, p);
	}

}
