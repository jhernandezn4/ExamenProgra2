package umg.DAO;

import umg.DTO.EstudiantesDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class EstudiantesDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public EstudiantesDTO crear(EstudiantesDTO estudiante){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            System.out.println(estudiante.getEstudianteId());
            session.persist(estudiante);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return this.leer(estudiante.getCarnet());
    }
    public List<EstudiantesDTO> listar(){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM EstudiantesDTO ";
            Query<EstudiantesDTO> query = session.createQuery(q, EstudiantesDTO.class);
            List<EstudiantesDTO> estudiantes = query.list();


            return estudiantes;

        }catch (Exception e) {
            return null;
        }

    }
    public EstudiantesDTO leer(String carnet ){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM EstudiantesDTO WHERE carnet = :carnet";
            Query<EstudiantesDTO> query = session.createQuery(q, EstudiantesDTO.class);
            query.setParameter("carnet", carnet);

            EstudiantesDTO estudiante = query.uniqueResult();

            return estudiante;

        }catch (Exception e) {
            return null;
        }

    }
    public EstudiantesDTO actualizar(EstudiantesDTO estudiante){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(estudiante);
            session.getTransaction().commit();
            return estudiante;
        }catch (Exception e) {
            return null;
        }
    }
    public boolean eliminar(EstudiantesDTO estudiante){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(estudiante);

            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;

        }
    }

}
