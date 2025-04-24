package br.com.unisagrado.Unisagrado.unieventos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "usuario_id")
	private String userId;
	
	private String token;
	
	@Column(name = "stay_logged")
	private boolean stayLogged;

	public Profile(String userId, String token, boolean stayLogged) {
		this.userId = userId;
		this.token = token;
		this.stayLogged = stayLogged;
	}
	
	public Profile() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isStayLogged() {
		return stayLogged;
	}

	public void setStayLogged(boolean stayLogged) {
		this.stayLogged = stayLogged;
	}
	
}
