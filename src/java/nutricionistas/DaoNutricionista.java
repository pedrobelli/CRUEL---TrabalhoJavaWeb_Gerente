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
    
    public void delete(Nutricionista nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.delete(nutricionista);
        tx.commit();
    }
    
}
