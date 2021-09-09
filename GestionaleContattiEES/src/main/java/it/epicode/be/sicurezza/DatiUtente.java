package it.epicode.be.sicurezza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.epicode.be.model.Ruolo;
import it.epicode.be.model.Utente;
import lombok.Data;

@Data
public class DatiUtente implements UserDetails {
	
	private Long id;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = false;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	private Date expirationTime;
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public DatiUtente(Long id, String username, String email, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
		accountNonLocked = enabled;
		accountNonExpired = enabled;
		credentialsNonExpired = enabled;
		}
	
	
	public static DatiUtente metodoFactory(Utente utente) {
		List<GrantedAuthority> listaRuoli = new ArrayList<>();
		for(Ruolo r:utente.getRuoli()) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(r.getRuoloTipo().name());
			listaRuoli.add(authority);
		}
		
		DatiUtente pacchettoDatiUtente = new DatiUtente(utente.getId(), utente.getUserName(), utente.getEmail(), utente.getPassword(),
				utente.isActive(),listaRuoli);
		return pacchettoDatiUtente;
	}
	

	

}
