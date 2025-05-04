package br.com.unisagrado.Unisagrado.unieventos.users.dto;

public class UserPasswordRecoveryDTO {

	public String email;

	public UserPasswordRecoveryDTO() {
	}

	public UserPasswordRecoveryDTO(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
