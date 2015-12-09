package usuarios;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class DaoUsuario {   
    private Session session;
    
    public DaoUsuario setDaoUsuario(Session session) {
        this.setSession(session);
        
        return this;
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public boolean checkExistance(String email) {
        Query query = this.session.createSQLQuery("SELECT * FROM usuarios WHERE email = :email").addEntity(Usuario.class).setParameter("email", email);
        List usuarios = query.list();
        
        return usuarios.size() > 0;
    }   
    
    public Object getByOwner(int idDono) {
        Query query = this.session.createSQLQuery("SELECT * FROM usuarios WHERE id_dono = :id_dono").addEntity(Usuario.class).setParameter("id_dono", idDono);
        List usuarios = query.list();
        
        Usuario usuario = (Usuario) usuarios.get(0);
        return usuario;
    }    
    
    public Object getByEmailESenha(Usuario usuario) {
        Query query = this.session.createSQLQuery("SELECT * FROM usuarios WHERE email = :email AND senha = :senha").addEntity(Usuario.class);
        query.setParameter("email", usuario.getEmail());
        query.setParameter("senha", usuario.getSenha());
        List usuarios = query.list();
        
        Usuario usuarioPersistido = new Usuario();
        
        if (usuarios.size() > 0) {
            usuarioPersistido = (Usuario) usuarios.get(0);
        }
        
        return usuarioPersistido;
    }   
    
    public Object create(Usuario usuario) {
        this.session.save(usuario);
        
        return usuario;
    }

    public Object update(Usuario usuario) {
        Usuario usuarioPersistido = (Usuario) this.session.load(Usuario.class, usuario.getEmail());
        usuarioPersistido = usuario;
        this.session.update(usuarioPersistido);
        
        return usuarioPersistido;
    }
    
    public void delete(Usuario usuario) {
        this.session.delete(usuario);
    } 
    
}
