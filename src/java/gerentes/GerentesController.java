/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerentes;

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
 * @author pedro
 */
@WebServlet(name = "GerentesController", urlPatterns = {"/gerentes"})
public class GerentesController extends HttpServlet {
    
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
                
                request.setAttribute("gerentes", this.all()); 
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
          
            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");
                
                if (!searchQuery.equals("")) {
                    request.setAttribute("gerentes", this.search(searchQuery));
                } else {
                    request.setAttribute("gerentes", this.all());
                }
                 
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
                
            } else if (action.equals("new")) {
                
                request.setAttribute("gerente", new Gerente());
                getServletContext().getRequestDispatcher("/gerente/gerentes/new.jsp").forward(request, response);
                
            } else if (action.equals("create")) {
                List<String> errors = this.validate(request);
                
                this.validateCreate(request, errors);
                
                Gerente gerente = this.processRequestForm(request);
                this.create(gerente);
                
                request.setAttribute("gerentes", this.all());
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
                
            } else if (action.equals("edit")) {
                DaoGerente daoGerente = new DaoGerente();
                int id = Integer.parseInt(request.getParameter("id"));
                
                request.setAttribute("gerente", daoGerente.get(id));
                getServletContext().getRequestDispatcher("/gerente/gerentes/edit.jsp").forward(request, response);
                
            } else if (action.equals("update")) {
                
                /*this.validate(request);*/
                
                Gerente gerente = this.processRequestForm(request);
                this.update(gerente);
                
                request.setAttribute("gerentes", this.all());
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
                
            } else if (action.equals("delete")) {
                
                Gerente gerente = new Gerente();
                gerente.setId(Integer.parseInt(request.getParameter("id")));
                
                this.delete(gerente);
                
                request.setAttribute("gerentes", this.all());
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
            
            }
            
        } catch (Exception E) {
            request.setAttribute("gerente", this.processRequestForError(request));
            request.setAttribute("usuario", Usuario.processRequestForError(request));
            
            String action = request.getParameter("action");
            
            if (action != null && action.equals("create")) {
                getServletContext().getRequestDispatcher("/gerente/gerentes/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                getServletContext().getRequestDispatcher("/gerente/gerentes/edit.jsp").forward(request, response);
            }
            
        } finally {
            out.close();
        }
    }
    
    public List<Gerente> all() throws SQLException {
        List<Gerente> gerentes = new ArrayList<Gerente>();
        
        DaoGerente daoGerente = new DaoGerente();
        gerentes = (List) daoGerente.all();
        
        return gerentes;
    }
    
    public List<Gerente> search(String searchQuery) throws SQLException {
        List<Gerente> gerentes = new ArrayList<Gerente>();
        
        DaoGerente daoGerente = new DaoGerente();
        gerentes = (List) daoGerente.search(searchQuery);
        
        return gerentes;
    }
    
    public void create(Gerente gerente) throws SQLException {
        DaoGerente daoGerente = new DaoGerente();
        daoGerente.create(gerente);
    }
    
    public void update(Gerente gerente) throws SQLException {
        DaoGerente daoGerente = new DaoGerente();
        daoGerente.update(gerente);
    }
    
    public void delete(Gerente gerente) throws SQLException {
        DaoGerente daoGerente = new DaoGerente();
        daoGerente.delete(gerente);
    }
    
    private List<String> validate(HttpServletRequest request) throws Exception {
        List<String> errors = new ArrayList<>();
        
        if (request.getParameter("nome").length() < 1) {
            errors.add("O campo nome deve ser preenchido;");
        }

        if (request.getParameter("cpf").length() < 1) {
            errors.add("O campo cpf deve ser preenchido;");
        } else {
            String cpf = request.getParameter("cpf");
            if (cpf.length() != 11) {
                errors.add("O campo cpf deve conter 11 digitos;");
            }
        }

        
        String cep = request.getParameter("cep");
        if (cep.length() > 0 && cep.length() != 8) {
            errors.add("O campo cep deve conter 8 digitos;");
        }

        String numeroTelefone = request.getParameter("numeroTelefone");
        if (numeroTelefone.length() > 0) {
            if (numeroTelefone.length() < 10) {
                errors.add("O campo telefone deve conter no mínimo 10 digitos;");
            } else if (numeroTelefone.length() > 11) {
                errors.add("O campo telefone não pode conter mais de 11 digitos;");
            }
        }

        String numeroCelular = request.getParameter("numeroCelular");
        if (numeroCelular.length() > 0) {
            if (numeroCelular.length() < 10) {
                errors.add("O campo celular deve conter no mínimo 10 digitos;");
            } else if (numeroCelular.length() > 11) {
                errors.add("O campo celular não pode conter mais de 11 digitos;");
            }
        }
        
        return errors; 
    }
    
    private void validateCreate(HttpServletRequest request, List<String> errors) throws Exception {
        if (request.getParameter("email").length() < 1) {
            errors.add("O campo email deve ser preenchido;");
        }
        
        String senha = request.getParameter("senha");
        String confirmSenha = request.getParameter("confirmSenha");
        
        if (senha.length() < 1 || confirmSenha.length() < 1) {
            errors.add("Os campo senha e confirma senha devem ser preenchidos;");
        } else {
            if (!senha.equals(confirmSenha)) {
                errors.add("Os campo senha e confirma senha estão diferentes;");
            }
        }
        
        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
        }   
    }
    
    private Gerente processRequestForm(HttpServletRequest request) {
        Gerente geren = new Gerente();
        
        if (request.getParameter("id") != null) {
            geren.setId(Integer.parseInt(request.getParameter("id")));
        }

        geren.setNome(request.getParameter("nome"));
        geren.setCpf(request.getParameter("cpf"));
        //Endereço
        geren.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado") != null && request.getParameter("estado").length() > 0) {
            geren.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        geren.setCidade(request.getParameter("cidade"));
        geren.setBairro(request.getParameter("bairro"));
        geren.setRua(request.getParameter("rua"));
        
        if (request.getParameter("numeroEndereco").length() > 0) {
            geren.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        geren.setComplemento(request.getParameter("complemento"));
        //Telefone
        if (request.getParameter("numeroTelefone").length() > 0) {
            String telefone = request.getParameter("numeroTelefone");
            geren.setCodigoAreaTelefone(Integer.parseInt(telefone.substring(0, 2)));
            geren.setNumeroTelefone(telefone.substring(2));
        }
        
        if (request.getParameter("numeroCelular").length() > 0) {
            String celular = request.getParameter("numeroCelular");
            geren.setCodigoAreaCelular(Integer.parseInt(celular.substring(0, 2)));
            geren.setNumeroCelular(celular.substring(2));
        }

        return geren;
    }

    private Gerente processRequestForError(HttpServletRequest request) {
        Gerente geren = new Gerente();
        
        if (request.getParameter("id") != null) {
            geren.setId(Integer.parseInt(request.getParameter("id")));
        }

        geren.setNome(request.getParameter("nome"));
        geren.setCpf(request.getParameter("cpf"));
        //Endereço
        geren.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado") != null && request.getParameter("estado").length() > 0) {
            geren.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        geren.setCidade(request.getParameter("cidade"));
        geren.setBairro(request.getParameter("bairro"));
        geren.setRua(request.getParameter("rua"));
        
        if (request.getParameter("numeroEndereco").length() > 0) {
            geren.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        geren.setComplemento(request.getParameter("complemento"));
        //Telefone
        geren.setNumeroTelefone(request.getParameter("numeroTelefone"));
        geren.setNumeroCelular(request.getParameter("numeroCelular"));

        return geren;
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
            Logger.getLogger(GerentesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GerentesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GerentesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GerentesController.class.getName()).log(Level.SEVERE, null, ex);
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
