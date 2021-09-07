package it.epicode.be.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Comune;
import it.epicode.be.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	private ComuneRepository comuneRepo;

	public Page<Comune> getAllComuni(Pageable p) {
		return comuneRepo.findAll(p);
	}

	public Comune aggiungiComune(Comune comune) {
		return comuneRepo.save(comune);
	}

	public Optional<Comune> getComuneById(Long id) {
		Optional<Comune> trovato = comuneRepo.findById(id);
		return trovato;
	}

	public void deleteComuneById(Long id) {
		Optional<Comune> comune = comuneRepo.findById(id);
		comuneRepo.deleteById(id);
	}

	public void updateComune(Comune comune) {
		Optional<Comune> oc = comuneRepo.findById(comune.getId());
		if (oc.isEmpty()) {
			throw new EntityNotFoundException("Il comune con id " + comune.getId() + " non esiste");
		} else {
			comuneRepo.save(comune);

		}
	}

}
