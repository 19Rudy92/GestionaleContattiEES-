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
import it.epicode.be.service.FatturaService;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

	@Autowired
	private FatturaService fatturaServ;

	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<Page<Fattura>> listaFatture(Pageable p) {
		Page<Fattura> pf = fatturaServ.getAllFatture(p);
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public Optional<Fattura> getFatturaById(@PathVariable Long id) {
		Optional<Fattura> trovata = fatturaServ.getFatturaById(id);
		return trovata;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> aggiungiFattura(@RequestBody Fattura fattura) {
		Fattura aggiunta = fatturaServ.aggiungiFattura(fattura);
		return new ResponseEntity<>(aggiunta, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFatturaById(@PathVariable Long id) {
		Optional<Fattura> daEliminare = fatturaServ.getFatturaById(id);
		if (daEliminare.isPresent()) {
			fatturaServ.deleteFatturaById(id);
			return new ResponseEntity<>("Cancellazione fattura avvenuta con successo", HttpStatus.OK);
		}
		return new ResponseEntity<>("L'id della fattura cercata non esiste", HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateFattura(@PathVariable Long id, @RequestBody Fattura fattura) {
		if (id != fattura.getId()) {
			return new ResponseEntity<>("L'id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		try {
			fatturaServ.updateFattura(fattura);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	

}
