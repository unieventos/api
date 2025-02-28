package br.com.unisagrado.Unisagrado.unieventos.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "usuario_id")
	private String id;
	
	@Column(nullable = false)
	private String curso;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToMany
	@JoinTable(
			  name = "usuario_evento_permissao", 
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "evento_id"))
	private Set<Evento> eventosPermissao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_acesso", nullable = false)
	private AcessoUsuario tipoAcesso;

	public Usuario() {
		this.setId(UUID.randomUUID().toString());
	}
	
	public Usuario(String id, String curso, String email, String senha, String nome, AcessoUsuario tipoAcesso) {
		this.id = id;
		this.curso = curso;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.tipoAcesso = tipoAcesso;
	}

	public Set<Evento> getEventosPermissao() {
		return eventosPermissao;
	}

	public void setEventosPermissao(Set<Evento> eventosPermissao) {
		this.eventosPermissao = eventosPermissao;
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

	public AcessoUsuario getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(AcessoUsuario tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

}
