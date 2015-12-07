package nutricionistas;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoNutricionista {
    
    public Object create(Nutricionista nutricionista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.save(nutricionista);
        tx.commit();

        return nutricionista;
    }
    
    public List all(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Nutricionistas").addEntity(Nutricionista.class);        
        List nutricionistas = query.list();
        
        return  nutricionistas;
    }
    
}
