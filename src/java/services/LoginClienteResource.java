/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.hibernate.Session;
import usuarios.DaoUsuario;
import usuarios.Usuario;
import utils.HibernateUtil;

/**
 * REST Web Service
 *
 * @author Layla
 */
@Path("LoginCliente")
public class LoginClienteResource {

    @Context
    private UriInfo context;
    
    private Session session;
     
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Creates a new instance of LoginClienteResource
     */
    public LoginClienteResource() {
    }

    /**
     * Retrieves representation of an instance of services.LoginClienteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Usuario getJson(String senha, String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {   
        this.setSession(HibernateUtil.getSessionFactory().openSession());
        
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(senha.getBytes("UTF-8"));
        Usuario usuario = new Usuario();
        
        usuario.setEmail(email);
        usuario.setSenha(new BigInteger(1,messageDigest.digest()).toString(16));

        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());
        Usuario usuarioPersistido = (Usuario) daoUsuario.getByEmailESenha(usuario);
        
        return usuarioPersistido;
    }

    /**
     * PUT method for updating or creating an instance of LoginClienteResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
