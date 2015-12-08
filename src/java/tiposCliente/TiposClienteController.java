/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiposCliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuarios.Usuario;

/**
 *
 * @author Layla
 */
@WebServlet(name = "TiposClienteController", urlPatterns = {"/tiposCliente"})
public class TiposClienteController extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            this.setRequest(request);
            this.setResponse(response);
            
            String action = request.getParameter("action");
            
            if (action == null) {
                
                request.setAttribute("tiposCliente", this.all()); 
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);
          
            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");
                
                if (!searchQuery.equals("")) {
                    request.setAttribute("tiposCliente", this.search(searchQuery));
                } else {
                    request.setAttribute("tiposCliente", this.all());
                }
                 
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);
                
            } else if (action.equals("new")) {
               
                request.setAttribute("tipoCliente", new TipoCliente());
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/new.jsp").forward(request, response);
                
            } else if (action.equals("create")) {
                List<String> errors = this.validate(request);
                
                this.validateCreate(request, errors);
                
                TipoCliente tipoCliente = this.processRequestForm(request);
                this.create(tipoCliente);
                
                request.setAttribute("tiposCliente", this.all());
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);
                
            } else if (action.equals("edit")) {
                DaoTipoCliente daoTipoCliente = new DaoTipoCliente();
                int id = Integer.parseInt(request.getParameter("id"));
                
                request.setAttribute("tipoCliente", daoTipoCliente.get(id));
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/edit.jsp").forward(request, response);
                
            } else if (action.equals("update")) {
                
                /*this.validate(request);*/
                
                TipoCliente tipoCliente = this.processRequestForm(request);
                this.update(tipoCliente);
                
                request.setAttribute("tiposCliente", this.all());
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);
                
            } else if (action.equals("delete")) {
                
                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setId(Integer.parseInt(request.getParameter("id")));
                
                this.delete(tipoCliente);
                
                request.setAttribute("tiposCliente", this.all());
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);
            
            }
            
        } catch (Exception E) {
            request.setAttribute("tipoCliente", this.processRequestForError(request));
            request.setAttribute("usuario", Usuario.processRequestForError(request));
            
            String action = request.getParameter("action");
            
            if (action != null && action.equals("create")) {
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/edit.jsp").forward(request, response);
            }
            
        } finally {
            out.close();
        }
    }
    public List<TipoCliente> all() throws SQLException {
        List<TipoCliente> tiposCliente = new ArrayList<TipoCliente>();
        
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente();
        tiposCliente = (List) daoTipoCliente.all();
        
        return tiposCliente;
    }
    
    public List<TipoCliente> search(String searchQuery) throws SQLException {
        List<TipoCliente> tiposCliente = new ArrayList<TipoCliente>();
        
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente();
        tiposCliente = (List) daoTipoCliente.search(searchQuery);
        
        return tiposCliente;
    }
    
    public void create(TipoCliente tipoCliente) throws SQLException {
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente();
        daoTipoCliente.create(tipoCliente);
    }
    
    public void update(TipoCliente tipoCliente) throws SQLException {
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente();
        daoTipoCliente.update(tipoCliente);
    }
    
    public void delete(TipoCliente tipoCliente) throws SQLException {
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente();
        daoTipoCliente.delete(tipoCliente);
    }
    
    private List<String> validate(HttpServletRequest request) throws Exception {
        List<String> errors = new ArrayList<>();
        
        if (request.getParameter("nome").length() < 1) {
            errors.add("O campo nome deve ser preenchido;");
        }
        
        return errors; 
    }
    
    private void validateCreate(HttpServletRequest request, List<String> errors) throws Exception {
        if (request.getParameter("name").length() < 1) {
            errors.add("O campo Nome deve ser preenchido;");
        }
        
        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
        }   
    }
    
    private TipoCliente processRequestForm(HttpServletRequest request) {
        TipoCliente tipo = new TipoCliente();
        
        if (request.getParameter("id") != null) {
            tipo.setId(Integer.parseInt(request.getParameter("id")));
        }

        tipo.setNome(request.getParameter("nome"));          

        return tipo;
    }

    private TipoCliente processRequestForError(HttpServletRequest request) {
        TipoCliente tipo = new TipoCliente();
        
        if (request.getParameter("id") != null) {
            tipo.setId(Integer.parseInt(request.getParameter("id")));
        }

        tipo.setNome(request.getParameter("nome"));
        
        return tipo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
