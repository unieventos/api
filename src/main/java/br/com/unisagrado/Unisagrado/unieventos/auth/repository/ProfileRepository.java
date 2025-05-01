package br.com.unisagrado.Unisagrado.unieventos.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

	Optional<Profile> findByToken(String token);
	List<Profile> findByUserId(String userId);
}
