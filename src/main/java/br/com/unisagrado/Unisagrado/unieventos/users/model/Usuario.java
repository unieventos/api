package br.com.unisagrado.Unisagrado.unieventos.users.model;

import java.util.Set;
import java.util.UUID;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private Course curso;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;
	
	@Column(nullable = false, name = "is_active")
	private boolean active;
	
	@ManyToMany
	@JoinTable(
			  name = "usuario_evento_permissao", 
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "evento_id"))
	private Set<Evento> eventosPermissao;

	public Usuario() {
		this.setId(UUID.randomUUID().toString());
	}

	public Usuario(String id, String login, Course curso, String email, String senha, String nome, String sobrenome, boolean active, Set<Evento> eventosPermissao, Role role) {
		this.id = id;
		this.login = login;
		this.curso = curso;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.active = active;
		this.eventosPermissao = eventosPermissao;
		this.role = role;
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

	public Course getCurso() {
		return curso;
	}

	public void setCurso(Course curso) {
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
