package br.com.unisagrado.Unisagrado.unieventos.model;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;
    
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Usuario> usuario;

    public Curso() {
    }
    

	public Curso(Long id, String nome, List<Usuario> usuario) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


	public List<Usuario> getUsuario() {
		return usuario;
	}


	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

}
