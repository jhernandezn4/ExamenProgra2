package umg.DAO;

import umg.DTO.UsuariosDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UsuariosDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public UsuariosDTO crear(UsuariosDTO usuario){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return this.leer(usuario.getUsuario());
    }
    public UsuariosDTO leer(String nombreUsuario ){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String q = "FROM UsuariosDTO WHERE usuario = :usuario";
            Query<UsuariosDTO> query = session.createQuery(q, UsuariosDTO.class);
            query.setParameter("usuario", nombreUsuario);

            UsuariosDTO usuario = query.uniqueResult();
            return usuario;

        }catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }


}
