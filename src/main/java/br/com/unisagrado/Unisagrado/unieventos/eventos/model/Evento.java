package br.com.unisagrado.Unisagrado.unieventos.eventos.model;

import java.time.LocalDate;
import java.util.Set;

import br.com.unisagrado.Unisagrado.unieventos.categoria.model.Categoria;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento implements ContemFoto {

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

	@ManyToOne
    @JoinColumn(name = "usuario_criador")
	private Usuario usuarioCriador;

	@ManyToMany
	@JoinTable(name = "usuario_evento_permissao", joinColumns = @JoinColumn(name = "evento_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> usuariosPermissao;

	@ManyToMany
	@JoinTable(name = "evento_categoria", joinColumns = @JoinColumn(name = "evento_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> eventoCategoria;

	public Evento() {
	}

	public Evento(String id, String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim,
			Usuario usuarioCriador, Set<Usuario> usuariosPermissao, Set<Categoria> eventoCategoria) {
		this.id = id;
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.usuarioCriador = usuarioCriador;
		this.usuariosPermissao = usuariosPermissao;
		this.eventoCategoria = eventoCategoria;
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

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public Set<Usuario> getUsuariosPermissao() {
		return usuariosPermissao;
	}

	public void setUsuariosPermissao(Set<Usuario> usuariosPermissao) {
		this.usuariosPermissao = usuariosPermissao;
	}

	public Set<Categoria> getEventoCategoria() {
		return eventoCategoria;
	}

	public void setEventoCategoria(Set<Categoria> eventoCategoria) {
		this.eventoCategoria = eventoCategoria;
	}

	@Override
	public String nome() {
		return "Evento";
	}
}
