package umg.DTO;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cursos", schema = "public", catalog = "proyecto")
public class CursosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cursos_secuencia")
    @SequenceGenerator(name = "cursos_secuencia", sequenceName = "cursos_seq", allocationSize = 1)
    @Column(name = "cursos_id", nullable = false)
    private int cursosId;
    @Basic
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Basic
    @Column(name = "precio", nullable = true, precision = 0)
    private BigInteger precio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "curso", cascade = CascadeType.ALL)
    private Collection<InscripcionesDTO> estudiantes;
    @Basic
    @Column(name = "codigo", nullable = true, length = 255)
    private String codigo;

    public int getCursosId() {
        return cursosId;
    }

    public void setCursosId(int cursosId) {
        this.cursosId = cursosId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getPrecio() {
        return precio;
    }

    public void setPrecio(BigInteger precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursosDTO cursosDTO = (CursosDTO) o;
        return cursosId == cursosDTO.cursosId && Objects.equals(nombre, cursosDTO.nombre) && Objects.equals(precio, cursosDTO.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursosId, nombre, precio);
    }

    public Collection<InscripcionesDTO> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Collection<InscripcionesDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
