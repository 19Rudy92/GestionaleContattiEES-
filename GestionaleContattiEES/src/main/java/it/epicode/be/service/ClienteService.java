package it.epicode.be.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import it.epicode.be.dto.ClienteDTO;
import it.epicode.be.model.Cliente;
import it.epicode.be.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public Page<Cliente> getAllClienti(Pageable p){
		return clienteRepo.findAll(p);
	}
	
	public Cliente aggiungiCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}
	
	public Optional<Cliente> findClienteById(Long id){
		Optional<Cliente> trovato = clienteRepo.findById(id);
		return trovato;
	}
	
	public void deleteClienteById(Long id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		clienteRepo.deleteById(id);
	}
	
	public void updateCliente(Cliente cliente) {
		Optional<Cliente> oc = clienteRepo.findById(cliente.getId());
		if(oc.isEmpty()) {
			throw new EntityNotFoundException("Il cliente con id " + cliente.getId() + " non esiste");
		} 
		else {
			clienteRepo.save(cliente);
		}
	}
	
	public Page<Cliente> findByParteDelNome(String nome, Pageable p){
		return clienteRepo.findByRagioneSocialeContainingIgnoreCase(nome, p);
	}
	
	public Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(BigDecimal fatturatoAnnuale, Pageable p){
		return clienteRepo.findByFatturatoAnnualeGreaterThanEqual(fatturatoAnnuale, p);
	}
	
	public Page<Cliente> findByFatturatoAnnualeLessThanEqual(BigDecimal fatturatoAnnuale, Pageable p){
		return clienteRepo.findByFatturatoAnnualeLessThanEqual(fatturatoAnnuale, p);
	}

	public Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal fatturatoAnnualeMinimo,
			BigDecimal fatturatoAnnualeMassimo, Pageable p) {
		return clienteRepo.findByFatturatoAnnualeBetween(fatturatoAnnualeMinimo,
				fatturatoAnnualeMassimo, p);
	}
	
	
	
	
	
	

}
