package br.com.unisagrado.Unisagrado.unieventos.users.model;

import java.util.Set;
import java.util.UUID;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.model.Curso;
import br.com.unisagrado.Unisagrado.unieventos.model.Evento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "usuario_id")
	private String id;

	@Column(nullable = false, unique = true)
	private String login;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;
	
	
	@Column(nullable = false, name = "is_active")
	private boolean isActive;
	
	@ManyToMany
	@JoinTable(
			  name = "usuario_evento_permissao", 
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "evento_id"))
	private Set<Evento> eventosPermissao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuario_role",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

	public Usuario() {
		this.setId(UUID.randomUUID().toString());
	}

	public Usuario(String id, String login, Curso curso, String email, String senha, String nome, String sobrenome, boolean isActive, Set<Evento> eventosPermissao, Set<Role> roles) {
		this.id = id;
		this.login = login;
		this.curso = curso;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.isActive = isActive;
		this.eventosPermissao = eventosPermissao;
		this.roles = roles;
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
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

	public boolean getActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
}
