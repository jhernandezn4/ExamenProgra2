package umg.DTO;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "inscripciones", schema = "public", catalog = "proyecto")
public class InscripcionesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscripciones_secuencia")
    @SequenceGenerator(name = "inscripciones_secuencia", sequenceName = "inscripciones_seq", allocationSize = 1)
    @Column(name = "inscripciones_id", nullable = false)
    private int inscripcionesId;
    @Basic
    @Column(name = "cursos_id", nullable = true)
    private Integer cursosId;
    @Basic
    @Column(name = "estudiantes_id",  nullable = true)
    private Integer estudiantesId;
    @ManyToOne
    @JoinColumn(name = "cursos_id", insertable=false, updatable=false, referencedColumnName = "cursos_id")
    private CursosDTO curso;
    @ManyToOne
    @JoinColumn(name = "estudiantes_id", insertable=false, updatable=false, referencedColumnName = "estudiante_id")
    private EstudiantesDTO estudiante;

    public int getInscripcionesId() {
        return inscripcionesId;
    }

    public void setInscripcionesId(int inscripcionesId) {
        this.inscripcionesId = inscripcionesId;
    }

    public Integer getCursosId() {
        return cursosId;
    }

    public void setCursosId(Integer cursosId) {
        this.cursosId = cursosId;
    }

    public Integer getEstudiantesId() {
        return estudiantesId;
    }

    public void setEstudiantesId(Integer estudiantesId) {
        this.estudiantesId = estudiantesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionesDTO that = (InscripcionesDTO) o;
        return inscripcionesId == that.inscripcionesId && Objects.equals(cursosId, that.cursosId) && Objects.equals(estudiantesId, that.estudiantesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inscripcionesId, cursosId, estudiantesId);
    }

    public CursosDTO getCurso() {
        return curso;
    }

    public void setCurso(CursosDTO curso) {
        this.curso = curso;
    }

    public EstudiantesDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudiantesDTO estudiante) {
        this.estudiante = estudiante;
    }
}
