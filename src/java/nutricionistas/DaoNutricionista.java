package nutricionistas;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoNutricionista {
    
    public List all() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Nutricionistas").addEntity(Nutricionista.class);        
        List nutricionistas = query.list();
        
        return  nutricionistas;
    }
    
    public Nutricionista get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Nutricionistas WHERE id = :id").addEntity(Nutricionista.class).setParameter("id", id);
        
        List nutricionistas = query.list();
        
        Nutricionista nutricionista = (Nutricionista) nutricionistas.get(0);

        return nutricionista;
    }
    
    public List search(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Nutricionistas WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Nutricionista.class);        
        List nutricionistas = query.list();
        
        return  nutricionistas;
    }
    
    public void create(Nutricionista nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.save(nutricionista);
        tx.commit();
    }

    public void update(Nutricionista nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        Nutricionista nutricionistaPersistido = this.get(nutricionista.getId());
        nutricionistaPersistido = nutricionista;
        session.update(nutricionistaPersistido);
        tx.commit();
    }
    
    public void delete(Nutricionista nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.delete(nutricionista);
        tx.commit();
    }
    
}
