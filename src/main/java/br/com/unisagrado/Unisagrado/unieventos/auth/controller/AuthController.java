package br.com.unisagrado.Unisagrado.unieventos.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisagrado.Unisagrado.config.security.CustomUserDetailsService;
import br.com.unisagrado.Unisagrado.config.security.JwtUtil;
import br.com.unisagrado.Unisagrado.unieventos.auth.dto.AuthRequest;
import br.com.unisagrado.Unisagrado.unieventos.auth.dto.AuthResponse;
import br.com.unisagrado.Unisagrado.unieventos.auth.service.ProfileService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationProvider authManager;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getLogin());
		final String token = jwtUtil.generateToken(userDetails, authRequest.getStayLogged());
		
		profileService.saveNewToken(authRequest.getLogin(), authRequest.getStayLogged(), token);
		
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
}
