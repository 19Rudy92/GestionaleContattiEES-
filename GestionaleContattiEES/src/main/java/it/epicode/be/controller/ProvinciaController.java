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
import it.epicode.be.model.Provincia;
import it.epicode.be.service.ProvinciaService;

@RestController
@RequestMapping("/api/province")
public class ProvinciaController {
	
	@Autowired
	private ProvinciaService provinciaServ;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<Page<Provincia>> listaProvince(Pageable p){
		Page<Provincia> pp = provinciaServ.getAllProvince(p);
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public Optional<Provincia> getProvinciaById(@PathVariable Long id){
		Optional<Provincia> trovata = provinciaServ.getProvinciaById(id);
		return trovata;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> aggiungiProvincia(@RequestBody Provincia pro){
		Provincia aggiunta = provinciaServ.aggiungiProvincia(pro);
		return new ResponseEntity<>(aggiunta, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProvinciaById(@PathVariable Long id){
		Optional<Provincia> daEliminare = provinciaServ.getProvinciaById(id);
		if(daEliminare.isPresent()) {
			provinciaServ.deleteProvinciaById(id);
			return new ResponseEntity<>("Cancellazione provincia avvenuta con successo", HttpStatus.OK);
		}
		return new ResponseEntity<>("L'id della provincia cercata non esiste", HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProvinciaById(@PathVariable Long id, @RequestBody Provincia prov) {
		if (id != prov.getId()) {
			return new ResponseEntity<>("L'id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		try {
			provinciaServ.updateProvincia(prov);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	

}
