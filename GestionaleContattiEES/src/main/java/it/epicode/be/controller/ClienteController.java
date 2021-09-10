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
import it.epicode.be.model.Cliente;
import it.epicode.be.service.ClienteService;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

	@Autowired
	private ClienteService clienteServ;

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> listaClienti(Pageable p) {
		Page<ClienteDTO> pcDto = clienteServ.getAllClienti(p).map(ClienteDTO::fromCliente);
		return new ResponseEntity<>(pcDto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> aggiungiCliente(@RequestBody ClienteDTO cDto) {
		Cliente c = cDto.toCliente();
		Cliente aggiunto = clienteServ.aggiungiCliente(c);
		return new ResponseEntity<>(ClienteDTO.fromCliente(aggiunto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ClienteDTO>> getClienteById(@PathVariable Long id) {
		Optional<ClienteDTO> ocDto = clienteServ.findClienteById(id).map(ClienteDTO::fromCliente);
		return new ResponseEntity<>(ocDto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClienteById(@PathVariable Long id){
		Optional<Cliente> daEliminare = clienteServ.findClienteById(id);
		if(daEliminare.isPresent()) {
			clienteServ.deleteClienteById(id);
			return new ResponseEntity<>("Cancellazione avvenuta con successo", HttpStatus.OK);
		}
		return new ResponseEntity<>("Cliente non trovato", HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
		if(id != clienteDTO.getId()) {
			return new ResponseEntity<>("L'id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		try {
			Cliente c = clienteDTO.toCliente();
			clienteServ.updateCliente(c);
			return new ResponseEntity<>("Update effettuato", HttpStatus.OK);
		}
		catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/ragione_sociale")
	public ResponseEntity<Page<ClienteDTO>> getListaClientiPerNomeCon(@RequestParam String nome, Pageable p) {
		List<ClienteDTO> lDto = clienteServ.findByParteDelNome(nome, p).stream().map(ClienteDTO::fromCliente)
				.collect(Collectors.toList());
		Page<ClienteDTO> page = new PageImpl<>(lDto);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/fatturato_annuale_minimo/{fatturatoAnnuale}")
	public ResponseEntity<Page<ClienteDTO>> getListaClientiPerFatturatoAnnualeMinimo(@PathVariable BigDecimal fatturatoAnnuale, Pageable p){
		List<ClienteDTO> lDto= clienteServ.findByFatturatoAnnualeGreaterThanEqual(fatturatoAnnuale, p).stream().map(ClienteDTO::fromCliente)
				.collect(Collectors.toList());
		Page<ClienteDTO> page = new PageImpl<>(lDto);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/fatturato_annuale_massimo")
	public ResponseEntity<Page<ClienteDTO>> getListaClientiPerFatturatoAnnualeMassimo(@RequestParam BigDecimal fatturatoAnnuale, Pageable p){
		List<ClienteDTO> lDto= clienteServ.findByFatturatoAnnualeLessThanEqual(fatturatoAnnuale, p).stream().map(ClienteDTO::fromCliente)
				.collect(Collectors.toList());
		Page<ClienteDTO> page = new PageImpl<>(lDto);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/fatturato_annuale_compreso")
	public ResponseEntity<Page<ClienteDTO>> getListaClientiPerFatturatoAnnualeDaA(@RequestParam BigDecimal fatturatoAnnualeMinimo, @RequestParam BigDecimal fatturatoAnnualeMassimo,
			Pageable p){
		List<ClienteDTO> lDto= clienteServ.findByFatturatoAnnualeBetween(fatturatoAnnualeMinimo, fatturatoAnnualeMassimo, p).stream().map(ClienteDTO::fromCliente)
				.collect(Collectors.toList());
		Page<ClienteDTO> page = new PageImpl<>(lDto);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/data_inserimento/{data}")
	public ResponseEntity<?> getClienteByDataInserimento(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate data, Pageable p){
		Page<ClienteDTO> pcDto = clienteServ.findByDataInserimento(data, p).map(ClienteDTO::fromCliente);
		return new ResponseEntity<>(pcDto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/data_ultimo_contatto/{data}")
	public ResponseEntity<?> getClienteByDataUltimoContatto(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate data, Pageable p){
		Page<ClienteDTO> pcDto = clienteServ.findByDataUltimoContatto(data, p).map(ClienteDTO::fromCliente);
		return new ResponseEntity<>(pcDto, HttpStatus.OK);
	}
	
	
	
	

}
