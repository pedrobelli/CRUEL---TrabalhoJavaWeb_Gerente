/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.hibernate.Session;
import tiposCliente.DaoTipoCliente;
import tiposCliente.TipoCliente;
import utils.HibernateUtil;

/**
 * REST Web Service
 *
 * @author Layla
 */
@Path("TiposCliente")
public class TiposClienteResource {

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
     * Creates a new instance of TiposClienteResource
     */
    public TiposClienteResource() {
    }

    /**
     * Retrieves representation of an instance of services.TiposClienteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<TipoCliente> getJson() {
        this.setSession(HibernateUtil.getSessionFactory().openSession());
        List<TipoCliente> tiposCliente = new ArrayList<TipoCliente>();

        DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
        tiposCliente = (List) daoTipoCliente.all(this.getSession());

        System.out.println(tiposCliente);
        return tiposCliente;
        
    }

    /**
     * PUT method for updating or creating an instance of TiposClienteResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
