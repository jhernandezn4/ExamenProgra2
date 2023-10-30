package umg.DAO;

import umg.DTO.CursosDTO;
import umg.DTO.EstudiantesDTO;
import umg.DTO.InscripcionesDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class InscripcionesDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public InscripcionesDTO crear(InscripcionesDTO inscripcion){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(inscripcion);
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return inscripcion;
    }
    public List<InscripcionesDTO> listar(){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM InscripcionesDTO ";
            Query<InscripcionesDTO> query = session.createQuery(q, InscripcionesDTO.class);
            List<InscripcionesDTO> inscripciones = query.list();


            return inscripciones;

        }catch (Exception e) {
            return null;
        }

    }
    public InscripcionesDTO leer(int cursos_id, int estudiantes_id ){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            String q = "FROM InscripcionesDTO WHERE cursosId = :curso and estudiantesId = :estudiante";

            Query<InscripcionesDTO> query = session.createQuery(q, InscripcionesDTO.class);

            query.setParameter("curso", cursos_id).setParameter("estudiante",estudiantes_id);
            new Throwable(query.getQueryString());

            InscripcionesDTO inscripcion = query.uniqueResult();

            return inscripcion;

        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    public InscripcionesDTO actualizar(InscripcionesDTO inscripcion){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(inscripcion);
            session.getTransaction().commit();
            return inscripcion;
        }catch (Exception e) {
            return null;
        }
    }
    public boolean eliminar(InscripcionesDTO inscripcion){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(inscripcion);

            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;

        }
    }
}
