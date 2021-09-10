package it.epicode.be.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import it.epicode.be.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{
	
	Provincia findByNomeProvincia(String nome);

}
