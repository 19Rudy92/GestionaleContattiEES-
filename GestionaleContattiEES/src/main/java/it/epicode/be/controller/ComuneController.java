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

import it.epicode.be.model.Comune;
import it.epicode.be.service.ComuneService;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {

	@Autowired
	private ComuneService comuneServ;

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<Comune>> listaComuni(Pageable p) {
		Page<Comune> pc = comuneServ.getAllComuni(p);
		return new ResponseEntity<>(pc, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> aggiungiComune(@RequestBody Comune comune) {
		Comune aggiunto = comuneServ.aggiungiComune(comune);
		return new ResponseEntity<>(aggiunto, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public Optional<Comune> getComuneById(@PathVariable Long id) {
		Optional<Comune> trovato = comuneServ.getComuneById(id);
		return trovato;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteComuneById(@PathVariable Long id) {
		Optional<Comune> daEliminare = comuneServ.getComuneById(id);
		if (daEliminare.isPresent()) {
			comuneServ.deleteComuneById(id);
			return new ResponseEntity<>("Cancellazione comune avvenuta con successo", HttpStatus.OK);
		}
		return new ResponseEntity<>("L'id del comune cercato non esiste", HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateComune(@PathVariable Long id, @RequestBody Comune comune) {
		if (id != comune.getId()) {
			return new ResponseEntity<>("L'id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		try {
			comuneServ.updateComune(comune);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	

}
