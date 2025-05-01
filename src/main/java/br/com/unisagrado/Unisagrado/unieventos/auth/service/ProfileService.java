package br.com.unisagrado.Unisagrado.unieventos.auth.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.auth.exception.AccessTokenNotFound;
import br.com.unisagrado.Unisagrado.unieventos.auth.model.Profile;
import br.com.unisagrado.Unisagrado.unieventos.auth.repository.ProfileRepository;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.users.repository.UsuarioRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void saveNewToken(String userLogin, Boolean stayLogged, String token) {
		String userId = usuarioRepository.findIdByLogin(userLogin).orElseThrow(UserNotFoundException::new);

		LocalDateTime expiration = null;

		if (!stayLogged) {
			expiration = LocalDateTime.now().plusHours(10);
		}

		profileRepository.save(new Profile(userId, token, expiration, stayLogged));
	}

	public void revokeTokensFromUser(String userLogin) {
		List<Profile> profiles = profileRepository.findByUserId(userLogin);
		profiles.forEach(n -> profileRepository.delete(n));
	}

	public void revokeToken(String token) {
		Profile profile = profileRepository.findByToken(token).orElseThrow(AccessTokenNotFound::new);
		profileRepository.delete(profile);
	}

	public Profile findProfileByToken(String token) {
		return profileRepository.findByToken(token).orElseThrow(AccessTokenNotFound::new);
	}
	
	public void refreshProfile(Profile profile) {
		profile.setExpire(LocalDateTime.now().plusHours(10));
		profileRepository.save(profile);
	}
}
