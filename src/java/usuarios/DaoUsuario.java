package usuarios;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class DaoUsuario {
    
    public Object create(Usuario nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.save(nutricionista);
        tx.commit();
        
        return nutricionista;
    }

    public Object update(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        Usuario usuarioPersistido = (Usuario) session.load(Usuario.class, usuario.getEmail());
        usuarioPersistido = usuario;
        session.update(usuarioPersistido);
        tx.commit();
        
        return usuarioPersistido;
    }
    
    public void delete(Usuario nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.delete(nutricionista);
        tx.commit();
    }
}
