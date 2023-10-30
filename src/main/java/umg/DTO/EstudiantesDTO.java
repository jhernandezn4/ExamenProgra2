package umg.DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "estudiantes", schema = "public", catalog = "proyecto")
public class EstudiantesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_secuencia")
    @SequenceGenerator(name = "estudiante_secuencia", sequenceName = "estudiantes_seq", allocationSize = 1)
    @Column(name = "estudiante_id", nullable = false)
    private int estudianteId;
    @Basic
    @Column(name = "carnet", nullable = false, length = 255)
    private String carnet;
    @Basic
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Basic
    @Column(name = "apellido", nullable = false, length = 255)
    private String apellido;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Collection<InscripcionesDTO> cursos= new ArrayList<InscripcionesDTO>();;


    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudiantesDTO that = (EstudiantesDTO) o;
        return estudianteId == that.estudianteId && Objects.equals(carnet, that.carnet) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudianteId, carnet, nombre, apellido);
    }

    public Collection<InscripcionesDTO> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<InscripcionesDTO> cursos) {
        this.cursos = cursos;
    }
}
