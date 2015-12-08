package usuarios;

import java.io.Serializable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import nutricionistas.DaoNutricionista;

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
    
    public static Usuario processRequestForm(HttpServletRequest request) {
        Usuario usu = new Usuario();
        
        usu.setEmail(request.getParameter("email"));
        usu.setSenha(request.getParameter("senha"));

        return usu;
    }
    
    public static Usuario processRequestForError(HttpServletRequest request) {
        Usuario usu = new Usuario();
        
        usu.setEmail(request.getParameter("email"));
        
        return usu;
    }
    
    public static void create(Usuario usuario) throws SQLException {
        DaoUsuario daoUsuario = new DaoUsuario();
        daoUsuario.create(usuario);
    }
    
    public static void update(Usuario usuario) throws SQLException {
        DaoUsuario daoUsuario = new DaoUsuario();
        daoUsuario.update(usuario);
    }
    
    public static void delete(Usuario usuario) throws SQLException {
        DaoUsuario daoUsuario = new DaoUsuario();
        daoUsuario.delete(usuario);
    }
    
}
