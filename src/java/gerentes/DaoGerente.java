/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerentes;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Layla
 */
public class DaoGerente {
    public List all() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Gerentes").addEntity(Gerente.class);        
        List gerentes = query.list();
        
        return  gerentes;
    }
    
    public Gerente get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Gerentes WHERE id = :id").addEntity(Gerente.class).setParameter("id", id);
        
        List gerentes = query.list();
        
        Gerente gerente = (Gerente) gerentes.get(0);

        return gerente;
    }   
    
    public Gerente checkExistance(String cpf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Gerentes WHERE cpf = :cpf").addEntity(Gerente.class).setParameter("cpf", cpf);
        
        List gerentes = query.list();
        
        Gerente gerente = (Gerente) gerentes.get(0);

        return gerente;
    }   
    
    public List search(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Gerentes WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Gerente.class);        
        List gerentes = query.list();
        
        return  gerentes;
    }
    
    public Object create(Gerente gerente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.save(gerente);
        tx.commit();
        
        return gerente;
    }

    public Object update(Gerente gerente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        Gerente gerentePersistido = this.get(gerente.getId());
        gerentePersistido = gerente;
        session.update(gerentePersistido);
        tx.commit();
        
        return gerentePersistido;
    }
    
    public void delete(Gerente gerente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.delete(gerente);
        tx.commit();
    }
}
