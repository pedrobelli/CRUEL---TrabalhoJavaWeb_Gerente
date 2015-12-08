package usuarios;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import nutricionistas.DaoNutricionista;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Usuario implements Serializable {
    private String email;
    private String senha;
    private int    tipoUsuario;
    private int    idDono;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdDono() {
        return idDono;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }
    
    public static Usuario processRequestForm(HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Usuario usu = new Usuario();
        String senha = request.getParameter("senha");
        
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(senha.getBytes("UTF-8"));
        
        usu.setEmail(request.getParameter("email"));
        usu.setSenha(new BigInteger(1,messageDigest.digest()).toString(16));

        return usu;
    }
    
    public static Usuario processRequestForError(HttpServletRequest request) {
        Usuario usu = new Usuario();
        
        usu.setEmail(request.getParameter("email"));
        
        return usu;
    }
    
    public static void create(Usuario usuario, Session session) throws SQLException {
        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(session);
        daoUsuario.create(usuario);
    }
    
    public static void update(Usuario usuario, Session session) throws SQLException {
        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(session);
        daoUsuario.update(usuario);
    }
    
    public static void delete(int idDono, Session session) throws SQLException {
        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(session);
        Usuario usuario = (Usuario) daoUsuario.getByOwner(idDono);
        daoUsuario.delete(usuario);
    }
    
}
