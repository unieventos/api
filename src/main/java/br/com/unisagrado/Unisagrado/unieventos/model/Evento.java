package br.com.unisagrado.Unisagrado.unieventos.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "evento_id")
	private String id;

	@Column(name = "nome_evento", nullable = false)
	private String nomeEvento;

	private String descricao;

	@Column(name = "data_inicio", nullable = false)
	private LocalDate dateInicio;

	@Column(name = "data_fim", nullable = false)
	private LocalDate dateFim;

	@Column(name = "usuario_criador", nullable = false)
	private String usuarioCriador;

	@ManyToMany
	@JoinTable(name = "usuario_evento_permissao", joinColumns = @JoinColumn(name = "evento_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> usuariosPermissao;

	@OneToMany(mappedBy = "evento")
	private Set<Foto> fotos;

	public Evento() {
	}

	public Evento(String id, String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim,
			String usuarioCriador) {
		this.id = id;
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.usuarioCriador = usuarioCriador;
	}

	public Evento(String id, String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim,
			String usuarioCriador, Set<Usuario> usuariosPermissao, Set<Foto> fotos) {
		this.id = id;
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.usuarioCriador = usuarioCriador;
		this.usuariosPermissao = usuariosPermissao;
		this.fotos = fotos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(LocalDate dateInicio) {
		this.dateInicio = dateInicio;
	}

	public LocalDate getDateFim() {
		return dateFim;
	}

	public void setDateFim(LocalDate dateFim) {
		this.dateFim = dateFim;
	}

	public String getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(String usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public Set<Usuario> getUsuariosPermissao() {
		return usuariosPermissao;
	}

	public void setUsuariosPermissao(Set<Usuario> usuariosPermissao) {
		this.usuariosPermissao = usuariosPermissao;
	}

	public Set<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}
}
