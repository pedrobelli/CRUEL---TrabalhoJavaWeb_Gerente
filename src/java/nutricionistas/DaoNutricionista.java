package nutricionistas;

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
    
}
