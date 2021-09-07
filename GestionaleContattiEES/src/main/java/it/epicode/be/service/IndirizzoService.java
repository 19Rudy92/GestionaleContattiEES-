package it.epicode.be.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.epicode.be.model.Fattura;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	private IndirizzoRepository indirizzoRepo;

	public Page<Indirizzo> getAllIndirizzi(Pageable p) {
		return indirizzoRepo.findAll(p);
	}

	public Indirizzo aggiungiIndirizzo(Indirizzo indirizzo) {
		return indirizzoRepo.save(indirizzo);
	}

	public Optional<Indirizzo> getIndirizzoById(Long id) {
		Optional<Indirizzo> trovato = indirizzoRepo.findById(id);
		return trovato;
	}

	public void deleteIndirizzoById(Long id) {
		Optional<Indirizzo> indirizzo = indirizzoRepo.findById(id);
		indirizzoRepo.deleteById(id);
	}

	public void updateIndirizzo(Indirizzo indirizzo) {
		Optional<Indirizzo> of = indirizzoRepo.findById(indirizzo.getId());
		if (of.isEmpty()) {
			throw new EntityNotFoundException("L'indirizzo con id " + indirizzo.getId() + " non esiste");
		} else {
			indirizzoRepo.save(indirizzo);
		}
	}
	
	
}
