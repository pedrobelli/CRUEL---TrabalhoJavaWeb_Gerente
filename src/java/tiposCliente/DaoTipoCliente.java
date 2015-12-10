package tiposCliente;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoTipoCliente {
    private Session session;    
     
    public DaoTipoCliente setDaoTipoCliente(Session session) {
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
        
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_cliente").addEntity(TipoCliente.class);        
        List tiposCliente = query.list();
        
        return  tiposCliente;
    }
    
    public TipoCliente get(int id) {
        
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_cliente WHERE id = :id").addEntity(TipoCliente.class).setParameter("id", id);
        
        List tiposCliente = query.list();
        System.out.println("===== GET 1 =====");
        System.out.println(tiposCliente);
        System.out.println(id);
        TipoCliente tipoCliente = (TipoCliente) tiposCliente.get(0);
        
        System.out.println("===== GET 1 =====");
        return tipoCliente;
    }   
    
    public boolean checkExistance(String cpf) {
        
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_cliente WHERE name = :name").addEntity(TipoCliente.class).setParameter("cpf", cpf);
        
         List tiposCliente = query.list();
        
        return tiposCliente.size() > 0;
    }   
    
    public List search(String searchQuery) {
        
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_cliente WHERE nome LIKE '%"+searchQuery+"%'").addEntity(TipoCliente.class);        
        List tiposCliente = query.list();
        
        return  tiposCliente;
    }
    
    public Object create(TipoCliente tipoCliente) {
        this.session.save(tipoCliente);
        
        return tipoCliente;
    }

    public Object update(TipoCliente tipoCliente) {
        System.out.println("===== DAO 1 =====");
        TipoCliente tipoClientePersistido = this.get(tipoCliente.getId());
        System.out.println("===== DAO 2 =====");
        tipoClientePersistido = tipoCliente;
        System.out.println("===== DAO 3 =====");
        this.session.merge(tipoClientePersistido);
        System.out.println("===== DAO 4 =====");
        
        return tipoClientePersistido;
    }
    
    public void delete(TipoCliente tipoCliente) {
       this.session.delete(tipoCliente);
    }
    
}
