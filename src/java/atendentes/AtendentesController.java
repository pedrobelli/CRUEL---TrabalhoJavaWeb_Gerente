
package atendentes;

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
@WebServlet(name = "AtendentesController", urlPatterns = {"/atendentes"})
public class AtendentesController extends HttpServlet {
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
                
                request.setAttribute("atendentes", this.all()); 
                getServletContext().getRequestDispatcher("/gerente/atendentes/index.jsp").forward(request, response);
          
            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");
                
                if (!searchQuery.equals("")) {
                    request.setAttribute("atendentes", this.search(searchQuery));
                } else {
                    request.setAttribute("atendentes", this.all());
                }
                 
                getServletContext().getRequestDispatcher("/gerente/atendentes/index.jsp").forward(request, response);
                
            } else if (action.equals("new")) {
                
                request.setAttribute("atendente", new Atendente());
                getServletContext().getRequestDispatcher("/gerente/atendentes/new.jsp").forward(request, response);
                
            } else if (action.equals("create")) {
                
                this.validate(request, response);
                
                Atendente atendente = this.processRequestForm(request);
                this.create(atendente);
                
                request.setAttribute("atendentes", this.all());
                getServletContext().getRequestDispatcher("/gerente/atendentes/index.jsp").forward(request, response);
                
            } else if (action.equals("edit")) {
                DaoAtendente daoAtendente = new DaoAtendente();
                int id = Integer.parseInt(request.getParameter("id"));
                
                request.setAttribute("atendente", daoAtendente.get(id));
                getServletContext().getRequestDispatcher("/gerente/atendentes/edit.jsp").forward(request, response);
                
            } else if (action.equals("update")) {
                
                this.validate(request, response);
                
                Atendente atendente = this.processRequestForm(request);
                this.update(atendente);
                
                request.setAttribute("atendentes", this.all());
                getServletContext().getRequestDispatcher("/gerente/atendentes/index.jsp").forward(request, response);
                
            } else if (action.equals("delete")) {
                
                Atendente atendente = new Atendente();
                atendente.setId(Integer.parseInt(request.getParameter("id")));
                
                this.delete(atendente);
                
                request.setAttribute("atendentes", this.all());
                getServletContext().getRequestDispatcher("/gerente/atendentes/index.jsp").forward(request, response);
            
            }
            
        } catch (Exception E) {
            request.setAttribute("atendente", this.processRequestForError(request));
            
            String action = request.getParameter("action");
            
            if (action != null && action.equals("create")) {
                getServletContext().getRequestDispatcher("/gerente/atendentes/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                getServletContext().getRequestDispatcher("/gerente/atendentes/edit.jsp").forward(request, response);
            }
            
        } finally {
            out.close();
        }
    }
    
    public List<Atendente> all() throws SQLException {
        List<Atendente> atendentes = new ArrayList<Atendente>();
        
        DaoAtendente daoAtendente = new DaoAtendente();
        atendentes = (List) daoAtendente.all();
        
        return atendentes;
    }
    
    public List<Atendente> search(String searchQuery) throws SQLException {
        List<Atendente> atendentes = new ArrayList<Atendente>();
        
        DaoAtendente daoAtendente = new DaoAtendente();
        atendentes = (List) daoAtendente.search(searchQuery);
        
        return atendentes;
    }
    
    public void create(Atendente atendente) throws SQLException {
        DaoAtendente daoAtendente = new DaoAtendente();
        daoAtendente.create(atendente);
    }
    
    public void update(Atendente atendente) throws SQLException {
        DaoAtendente daoAtendente = new DaoAtendente();
        daoAtendente.update(atendente);
    }
    
    public void delete(Atendente atendente) throws SQLException {
        DaoAtendente daoAtendente = new DaoAtendente();
        daoAtendente.delete(atendente);
    }
    
    private void validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
        }    
    }
    
    private Atendente processRequestForm(HttpServletRequest request) {
        Atendente atend = new Atendente();
        
        if (request.getParameter("id") != null) {
            atend.setId(Integer.parseInt(request.getParameter("id")));
        }

        atend.setNome(request.getParameter("nome"));
        atend.setCpf(request.getParameter("cpf"));
        //Endereço
        atend.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado").length() > 0) {
            atend.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        atend.setCidade(request.getParameter("cidade"));
        atend.setBairro(request.getParameter("bairro"));
        atend.setRua(request.getParameter("rua"));
        
        if (request.getParameter("numeroEndereco").length() > 0) {
            atend.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        atend.setComplemento(request.getParameter("complemento"));
        //Telefone
        if (request.getParameter("numeroTelefone").length() > 0) {
            String telefone = request.getParameter("numeroTelefone");
            atend.setCodigoAreaTelefone(Integer.parseInt(telefone.substring(0, 2)));
            atend.setNumeroTelefone(telefone.substring(2));
        }
        
        if (request.getParameter("numeroCelular").length() > 0) {
            String celular = request.getParameter("numeroCelular");
            atend.setCodigoAreaCelular(Integer.parseInt(celular.substring(0, 2)));
            atend.setNumeroCelular(celular.substring(2));
        }

        return atend;
    }
    
    private Atendente processRequestForError(HttpServletRequest request) {
        Atendente atend = new Atendente();
        
        if (request.getParameter("id") != null) {
            atend.setId(Integer.parseInt(request.getParameter("id")));
        }

        atend.setNome(request.getParameter("nome"));
        atend.setCpf(request.getParameter("cpf"));
        //Endereço
        atend.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado").length() > 0) {
            atend.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        atend.setCidade(request.getParameter("cidade"));
        atend.setBairro(request.getParameter("bairro"));
        atend.setRua(request.getParameter("rua"));
        
        if (request.getParameter("numeroEndereco").length() > 0) {
            atend.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        atend.setComplemento(request.getParameter("complemento"));
        //Telefone
        atend.setNumeroTelefone(request.getParameter("numeroTelefone"));
        atend.setNumeroCelular(request.getParameter("numeroCelular"));

        return atend;
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
            Logger.getLogger(AtendentesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AtendentesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AtendentesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AtendentesController.class.getName()).log(Level.SEVERE, null, ex);
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
