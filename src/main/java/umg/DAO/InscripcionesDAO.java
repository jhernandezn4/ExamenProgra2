package umg.DAO;

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
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return this.leer(inscripcion.getEstudiante().getCarnet(),inscripcion.getCurso().getCodigo());
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
    public InscripcionesDTO leer(String carnet, String codigo ){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM InscripcionesDTO WHERE carnet = :carnet and codigo = :codigo";
            Query<InscripcionesDTO> query = session.createQuery(q, InscripcionesDTO.class);
            query.setParameter("carnet", carnet).setParameter("codigo",codigo);

            InscripcionesDTO inscripcion = query.uniqueResult();

            return inscripcion;

        }catch (Exception e) {
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
