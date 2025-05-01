package br.com.unisagrado.Unisagrado.unieventos.auth.validator;

import java.time.LocalDateTime;

import br.com.unisagrado.Unisagrado.unieventos.auth.exception.TokenExpiredException;
import br.com.unisagrado.Unisagrado.unieventos.auth.model.Profile;

public class ProfileValidator {

	public static void profileExpired(Profile profile) {
		
		if(profile.isStayLogged())
			return;
		
		if(LocalDateTime.now().isAfter(profile.getExpire())) {
			throw new TokenExpiredException();
		}
	}
	
}
