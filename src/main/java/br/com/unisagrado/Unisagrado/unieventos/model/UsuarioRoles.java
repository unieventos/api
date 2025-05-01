package br.com.unisagrado.Unisagrado.unieventos.model;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_role")
public class UsuarioRoles {

    @EmbeddedId
    private UsuarioRolesId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    public UsuarioRoles() {
    }

    public UsuarioRoles(UsuarioRolesId id, Usuario usuario, Role role) {
        this.id = id;
        this.usuario = usuario;
        this.role = role;
    }

    public UsuarioRolesId getId() {
        return id;
    }

    public void setId(UsuarioRolesId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
