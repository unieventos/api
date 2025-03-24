package br.com.unisagrado.Unisagrado.unieventos.model;

public class UsuarioRolesId {

    private String usuarioId;
    private Long roleId;

    public UsuarioRolesId() {
    }

    public UsuarioRolesId(String usuarioId, Long roleId) {
        this.usuarioId = usuarioId;
        this.roleId = roleId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
