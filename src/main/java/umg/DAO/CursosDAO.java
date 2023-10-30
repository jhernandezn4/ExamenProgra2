package umg.DAO;

import umg.DTO.CursosDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CursosDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public CursosDTO crear(CursosDTO curso){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.persist(curso);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return this.leer(curso.getCodigo());
    }
    public List<CursosDTO> listar(){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM CursosDTO ";
            Query<CursosDTO> query = session.createQuery(q, CursosDTO.class);
            List<CursosDTO> cursos = query.list();


            return cursos;

        }catch (Exception e) {
            return null;
        }

    }
    public CursosDTO leer(String codigo ){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM CursosDTO WHERE codigo = :codigo";
            Query<CursosDTO> query = session.createQuery(q, CursosDTO.class);
            query.setParameter("codigo", codigo);

            CursosDTO curso = query.uniqueResult();

            return curso;

        }catch (Exception e) {
            return null;
        }

    }
    public CursosDTO actualizar(CursosDTO curso){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(curso);
            session.getTransaction().commit();
            return curso;
        }catch (Exception e) {
            return null;
        }
    }
    public boolean eliminar(CursosDTO curso){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(curso);

            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;

        }
    }
}
