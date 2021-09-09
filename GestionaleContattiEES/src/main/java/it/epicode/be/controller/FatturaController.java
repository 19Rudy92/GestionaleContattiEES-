package it.epicode.be.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.dto.ClienteDTO;
import it.epicode.be.dto.FatturaDTO;
import it.epicode.be.model.Fattura;
import it.epicode.be.service.FatturaService;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

	@Autowired
	private FatturaService fatturaServ;

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<FatturaDTO>> listaFatture(Pageable p) {
		Page<FatturaDTO> pf = fatturaServ.getAllFatture(p).map(FatturaDTO::fromFattura);
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<FatturaDTO> getFatturaById(@PathVariable Long id) {
		Optional<Fattura> trovata = fatturaServ.getFatturaById(id);
		FatturaDTO fDto = FatturaDTO.fromFattura(trovata.get());
		return new ResponseEntity<>(fDto, HttpStatus.OK);
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
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> getFatturaByClienteId(@PathVariable Long id, Pageable p){
		Page<FatturaDTO> pf = fatturaServ.getFattureByCliente(id, p).map(FatturaDTO::fromFattura);
		
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/data/{data}")
	public ResponseEntity<?> getFatturaByData(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate data, Pageable p){
		Page<FatturaDTO> pfDto = fatturaServ.getFatturaByData(data, p).map(FatturaDTO::fromFattura);
		return new ResponseEntity<>(pfDto, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/stato_fattura/{id}")
	public ResponseEntity<?> getFatturaByStatoFatturaId(@PathVariable Long id, Pageable p){
		Page<FatturaDTO> pf = fatturaServ.getFattureByIdStatoFattura(id, p).map(FatturaDTO::fromFattura);
		
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/anno/{anno}")
	public ResponseEntity<?> getFattureByAnno(@PathVariable int anno, Pageable p){
		Page<FatturaDTO> pfDto = fatturaServ.getFattureByAnno(anno, p).map(FatturaDTO::fromFattura);
		return new ResponseEntity<>(pfDto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/range_importo/")
	public ResponseEntity<Page<FatturaDTO>> getListaFatturePerImporto(@RequestParam BigDecimal importoMinimo, @RequestParam BigDecimal importoMassimo,
			Pageable p){
		Page<FatturaDTO> lDto= fatturaServ.findByRangeImportiBetween(importoMinimo, importoMassimo, p).map(FatturaDTO::fromFattura);
		return new ResponseEntity<>(lDto, HttpStatus.OK);
	}
	
	
	
	
	

}
