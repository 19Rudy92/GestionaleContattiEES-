package it.epicode.be.sicurezza;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Utente;
import it.epicode.be.repository.UtenteRepository;

@Service
public class ServizioSicurezza implements UserDetailsService{
	
	@Autowired
	private UtenteRepository utenteRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente u = utenteRepo.findByUserName(username);
		if(u != null) {
			return DatiUtente.metodoFactory(u);
		}
		else{
			throw new UsernameNotFoundException("Utente non trovato: " + username);
		}		
	}

}
