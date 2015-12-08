package gerentes;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoGerente {
    private Session session;
    
    public DaoGerente setDaoGerente(Session session) {
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
        Query query = this.session.createSQLQuery("SELECT * FROM Gerentes").addEntity(Gerente.class);        
        List gerentes = query.list();
        
        return  gerentes;
    }
    
    public Gerente get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM Gerentes WHERE id = :id").addEntity(Gerente.class).setParameter("id", id);
        List gerentes = query.list();
        
        Gerente gerente = (Gerente) gerentes.get(0);
        return gerente;
    }   
    
    public boolean checkExistance(String cpf) {
        Query query = this.session.createSQLQuery("SELECT * FROM Gerentes WHERE cpf = :cpf").addEntity(Gerente.class).setParameter("cpf", cpf);
        List gerentes = query.list();
        
        return gerentes.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM Gerentes WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Gerente.class);        
        List gerentes = query.list();
        
        return  gerentes;
    }
    
    public Object create(Gerente gerente) {
        this.session.save(gerente);        
        return gerente;
    }

    public Object update(Gerente gerente) {
        Gerente gerentePersistido = this.get(gerente.getId());
        gerentePersistido = gerente;
        this.session.update(gerentePersistido);
        
        return gerentePersistido;
    }
    
    public void delete(Gerente gerente) {
        this.session.delete(gerente);
    }
    
}
