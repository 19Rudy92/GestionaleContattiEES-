package it.epicode.be.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.model.StatoFattura;
import it.epicode.be.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {
	
	@Autowired
	private StatoFatturaRepository statoFatturaRepo;
	
	public Page<StatoFattura> getAllStatoFatture(Pageable p){
		return statoFatturaRepo.findAll(p);
	}
	
	public StatoFattura aggiungiStatoFattura(StatoFattura statoFattura){
		return statoFatturaRepo.save(statoFattura);
	}
	
	public Optional<StatoFattura> getStatoFatturaById(Long id){
		Optional<StatoFattura> trovato = statoFatturaRepo.findById(id);
		return trovato;
	}
	
	public void deleteStatoFatturaById(Long id) {
		Optional<StatoFattura> sf = statoFatturaRepo.findById(id);
		statoFatturaRepo.deleteById(id);
	}
	
	public void updateStatoFattura(StatoFattura sf) {
		Optional<StatoFattura> osf = statoFatturaRepo.findById(sf.getId());
		if(osf.isEmpty()) {
			throw new EntityNotFoundException("Lo stato fattura con id " + sf.getId() + " non esiste"); 
		}
		else {
			statoFatturaRepo.save(sf);
		}
	}
	
	

}
