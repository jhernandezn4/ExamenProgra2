package umg.DTO;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "proyecto")
public class UsuariosDTO {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuarios_seq")
    @SequenceGenerator(name = "usuarios_seq", sequenceName = "usuarios_seq", allocationSize = 1)
    @Id
    @Column(name = "usuarios_id", nullable = false)
    private int usuariosId;
    @Basic
    @Column(name = "usuario", nullable = true, length = 255)
    private String usuario;
    @Basic
    @Column(name = "nombre", nullable = true, length = 255)
    private String nombre;

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    private String password;

    public int getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(int usuariosId) {
        this.usuariosId = usuariosId;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuariosDTO that = (UsuariosDTO) o;
        return usuariosId == that.usuariosId && Objects.equals(usuario, that.usuario) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuariosId, usuario, nombre, password);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
