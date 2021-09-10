package it.epicode.be.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Provincia;
import it.epicode.be.repository.ProvinciaRepository;

@Service
public class ProvinciaService {
	
	@Autowired
	private ProvinciaRepository provinciaRepo;
	
	public Page<Provincia> getAllProvince(Pageable p){
		return provinciaRepo.findAll(p);
	}
	
	public Optional<Provincia> getProvinciaById(Long id){
		Optional<Provincia> trovata = provinciaRepo.findById(id);
		return trovata;
	}
	
	public Provincia aggiungiProvincia(Provincia pro) {
		return provinciaRepo.save(pro);
	}
	
	public void deleteProvinciaById(Long id) {
		Optional<Provincia> prov = provinciaRepo.findById(id);
		provinciaRepo.deleteById(id);
	}
	
	public void updateProvincia(Provincia prov) {
		Optional<Provincia> op = provinciaRepo.findById(prov.getId());
		if(op.isEmpty()) {
			throw new EntityNotFoundException("La provicnia con id " + prov.getId() + "non esiste");
		}
		else {
			provinciaRepo.save(prov);
		}
	}
	
	

}
