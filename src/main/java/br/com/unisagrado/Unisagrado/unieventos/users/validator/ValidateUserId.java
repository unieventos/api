package br.com.unisagrado.Unisagrado.unieventos.users.validator;

import java.util.UUID;

import br.com.unisagrado.Unisagrado.unieventos.users.exception.IllegarUserIdException;

public class ValidateUserId {

	public static void validate(String uuid) {
		try {
			UUID.fromString(uuid);
		}catch (IllegalArgumentException e) {
			throw new IllegarUserIdException();
		}
	}
}
