package br.com.unisagrado.Unisagrado.unieventos.users.validator;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class CreateUserPermissionValidator {
	
	public static void validate() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(n -> System.out.println(n.getAuthority()));
		
	}
}
