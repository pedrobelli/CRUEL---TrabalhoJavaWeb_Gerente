package atendentes;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DaoAtendente {
    private Session session;
    
    public DaoAtendente setDaoAtendente(Session session) {
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
        Query query = this.session.createSQLQuery("SELECT * FROM atendentes").addEntity(Atendente.class);        
        List atendentes = query.list();
        
        return  atendentes;
    }
    
    public Atendente get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM atendentes WHERE id = :id").addEntity(Atendente.class).setParameter("id", id);
        List atendentes = query.list();
        
        Atendente atendente = (Atendente) atendentes.get(0);
        return atendente;
    }   
    
    public boolean checkExistance(String cpf) {
        Query query = this.session.createSQLQuery("SELECT * FROM atendentes WHERE cpf = :cpf").addEntity(Atendente.class).setParameter("cpf", cpf);
        List atendentes = query.list();
        
        return atendentes.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM atendentes WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Atendente.class);        
        List atendentes = query.list();
        
        return  atendentes;
    }
    
    public Object create(Atendente atendente) {
        this.session.save(atendente);        
        return atendente;
    }

    public Object update(Atendente atendente) {
        Atendente atendentePersistido = this.get(atendente.getId());
        atendentePersistido = atendente;
        this.session.merge(atendentePersistido);
        
        return atendentePersistido;
    }
    
    public void delete(Atendente atendente) {
        this.session.delete(atendente);
    }
    
}
