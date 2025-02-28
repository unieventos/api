package br.com.unisagrado.Unisagrado.unieventos.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String curso;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private AcessoUsuario tipo_acesso;

	public Usuario() {
		this.setId(UUID.randomUUID().toString());
	}
	
	public Usuario(String id, String curso, String email, String senha, String nome, boolean status,
			AcessoUsuario tipo_acesso) {
		this.id = id;
		this.curso = curso;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.status = status;
		this.tipo_acesso = tipo_acesso;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public AcessoUsuario getTipo_acesso() {
		return tipo_acesso;
	}

	public void setTipo_acesso(AcessoUsuario tipo_acesso) {
		this.tipo_acesso = tipo_acesso;
	}

}
