/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atendentes;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pedro
 */
public class DaoAtendente {
    
    public List all() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Atendentes").addEntity(Atendente.class);        
        List atendentes = query.list();
        
        return  atendentes;
    }
    
    public Atendente get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Atendentes WHERE id = :id").addEntity(Atendente.class).setParameter("id", id);
        
        List atendentes = query.list();
        
        Atendente atendente = (Atendente) atendentes.get(0);

        return atendente;
    }
    
    public List search(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createSQLQuery("SELECT * FROM Atendentes WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Atendente.class);        
        List atendentes = query.list();
        
        return  atendentes;
    }
    
    public void create(Atendente atendente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.save(atendente);
        tx.commit();
    }
    
    public void update(Atendente atendente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        Atendente atendentePersistido = this.get(atendente.getId());
        atendentePersistido = atendente;
        session.update(atendentePersistido);
        tx.commit();
    }
    
    public void delete(Atendente atendente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        session.delete(atendente);
        tx.commit();
    }
}
