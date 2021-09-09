package it.epicode.be.controller.login;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
	private String token;
	
	private final String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> ruoli;
	private Date expirationTime;

}
