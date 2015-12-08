/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiposCliente;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Layla
 */
public class DaoTipoCliente {
     public List all() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM tipos_cliente").addEntity(TipoCliente.class);        
        List tiposCliente = query.list();
        
        return  tiposCliente;
    }
    
    public TipoCliente get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM tipos_cliente WHERE id = :id").addEntity(TipoCliente.class).setParameter("id", id);
        
        List tiposCliente = query.list();
        
        TipoCliente tipoCliente = (TipoCliente) tiposCliente.get(0);

        return tipoCliente;
    }   
    
    public TipoCliente checkExistance(String cpf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM tipos_cliente WHERE cpf = :cpf").addEntity(TipoCliente.class).setParameter("cpf", cpf);
        
        List tiposCliente = query.list();
        
        TipoCliente tipoCliente = (TipoCliente) tiposCliente.get(0);

        return tipoCliente;
    }   
    
    public List search(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM tipos_cliente WHERE nome LIKE '%"+searchQuery+"%'").addEntity(TipoCliente.class);        
        List tiposCliente = query.list();
        
        return  tiposCliente;
    }
    
    public Object create(TipoCliente tipoCliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.save(tipoCliente);
        tx.commit();
        
        return tipoCliente;
    }

    public Object update(TipoCliente tipoCliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        TipoCliente tipoClientePersistido = this.get(tipoCliente.getId());
        tipoClientePersistido = tipoCliente;
        session.update(tipoClientePersistido);
        tx.commit();
        
        return tipoClientePersistido;
    }
    
    public void delete(TipoCliente tipoCliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.delete(tipoCliente);
        tx.commit();
    }
}
