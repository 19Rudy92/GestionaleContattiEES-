package it.epicode.be.controller;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.model.Fattura;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.service.IndirizzoService;

@RestController
@RequestMapping("/api/indirizzi")
public class IndirizzoController {
	
	@Autowired
	private IndirizzoService indirizzoServ;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<Page<Indirizzo>> listaIndirizzi(Pageable p){
		Page<Indirizzo> pi = indirizzoServ.getAllIndirizzi(p);
		
		return new ResponseEntity<>(pi, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> aggiungiIndirizzo(@RequestBody Indirizzo indirizzo) {
		Indirizzo aggiunto = indirizzoServ.aggiungiIndirizzo(indirizzo);
		return new ResponseEntity<>(aggiunto, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public Optional<Indirizzo> getIndirizzoById(@PathVariable Long id) {
		Optional<Indirizzo> trovato = indirizzoServ.getIndirizzoById(id);
		return trovato;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteIndirizzoById(@PathVariable Long id) {
		Optional<Indirizzo> daEliminare = indirizzoServ.getIndirizzoById(id);
		if (daEliminare.isPresent()) {
			indirizzoServ.deleteIndirizzoById(id);
			return new ResponseEntity<>("Cancellazione indirizzo avvenuta con successo", HttpStatus.OK);
		}
		return new ResponseEntity<>("L'id dell'indirizzo cercato non esiste", HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateIndirizzo(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
		if (id != indirizzo.getId()) {
			return new ResponseEntity<>("L'id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		try {
			indirizzoServ.updateIndirizzo(indirizzo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	

}
