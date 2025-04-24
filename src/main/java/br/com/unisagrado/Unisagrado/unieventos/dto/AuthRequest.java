package br.com.unisagrado.Unisagrado.unieventos.dto;

public class AuthRequest {

	private String login;
	private String password;
	private Boolean stayLogged;

	public AuthRequest() {
	}

	public AuthRequest(String login, String password, Boolean stayLogged) {
		this.login = login;
		this.password = password;
		this.stayLogged = stayLogged;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStayLogged() {
		return stayLogged;
	}

	public void setStayLogged(Boolean stayLogged) {
		this.stayLogged = stayLogged;
	}

}