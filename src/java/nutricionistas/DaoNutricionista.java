package nutricionistas;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoNutricionista {
    private Session session;
    
    public DaoNutricionista setDaoNutricionista(Session session) {
        this.setSession(session);
        
        return this;
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    
    public List all(Session session) {
        Query query = this.session.createSQLQuery("SELECT * FROM Nutricionistas").addEntity(Nutricionista.class);        
        List nutricionistas = query.list();
        
        return  nutricionistas;
    }
    
    public Nutricionista get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM Nutricionistas WHERE id = :id").addEntity(Nutricionista.class).setParameter("id", id);
        List nutricionistas = query.list();
        
        Nutricionista nutricionista = (Nutricionista) nutricionistas.get(0);
        return nutricionista;
    }   
    
    public boolean checkExistance(String cpf) {
        Query query = this.session.createSQLQuery("SELECT * FROM Nutricionistas WHERE cpf = :cpf").addEntity(Nutricionista.class).setParameter("cpf", cpf);
        List nutricionistas = query.list();
        
        return nutricionistas.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM Nutricionistas WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Nutricionista.class);        
        List nutricionistas = query.list();
        
        return  nutricionistas;
    }
    
    public Object create(Nutricionista nutricionista) {
        this.session.save(nutricionista);
        
        return nutricionista;
    }

    public Object update(Nutricionista nutricionista) {
        Nutricionista nutricionistaPersistido = this.get(nutricionista.getId());
        nutricionistaPersistido = nutricionista;
        this.session.update(nutricionistaPersistido);
        
        return nutricionistaPersistido;
    }
    
    public void delete(Nutricionista nutricionista) {
        this.session.delete(nutricionista);
    }
    
}
