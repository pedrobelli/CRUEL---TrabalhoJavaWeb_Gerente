package nutricionistas;

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
import org.hibernate.Session;
import org.hibernate.Transaction;
import usuarios.DaoUsuario;
import usuarios.Usuario;
import utils.HibernateUtil;
import utils.TipoUsuarioEnum.TipoUsuario;

@WebServlet(name = "NutricionistasController", urlPatterns = {"/nutricionistas"})
public class NutricionistasController extends HttpServlet {
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Session session;
    private Transaction transaction;
    
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
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            this.setRequest(request);
            this.setResponse(response);
            this.setSession(HibernateUtil.getSessionFactory().openSession());
            this.setTransaction(this.getSession().beginTransaction());
            
            String action = request.getParameter("action");
            
            if (action == null) {
                
                request.setAttribute("nutricionistas", this.all()); 
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
          
            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");
                
                if (!searchQuery.equals("")) {
                    request.setAttribute("nutricionistas", this.search(searchQuery));
                } else {
                    request.setAttribute("nutricionistas", this.all());
                }
                 
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
                
            } else if (action.equals("new")) {
                
                request.setAttribute("nutricionista", new Nutricionista());
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/new.jsp").forward(request, response);
                
            } else if (action.equals("create")) {
                List<String> errors = this.validate();
                
                this.validateCreate(errors);
                
                Nutricionista nutricionista = this.processRequestForm();
                this.create(nutricionista);
                
                Usuario usuario = Usuario.processRequestForm(request);
                this.createUsuario(usuario, nutricionista.getId());
                
                request.setAttribute("nutricionistas", this.all());
                
                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
                
            } else if (action.equals("edit")) {
                DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
                int id = Integer.parseInt(request.getParameter("id"));
                
                request.setAttribute("nutricionista", daoNutricionista.get(id));
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/edit.jsp").forward(request, response);
                
            } else if (action.equals("update")) {
                
                /*this.validate(request);*/
                
                Nutricionista nutricionista = this.processRequestForm();
                this.update(nutricionista);
                
                request.setAttribute("nutricionistas", this.all());
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
                
            } else if (action.equals("delete")) {
                
                Nutricionista nutricionista = new Nutricionista();
                nutricionista.setId(Integer.parseInt(request.getParameter("id")));
                
                this.delete(nutricionista);
                
                request.setAttribute("nutricionistas", this.all());
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
            
            }
            
        } catch (Exception E) {            
            request.setAttribute("nutricionista", this.processRequestForError());
            request.setAttribute("usuario", Usuario.processRequestForError(request));
            
            String action = request.getParameter("action");
            
            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/edit.jsp").forward(request, response);
            }
            
        } finally {
            out.close();
        }
    }
    
    public List<Nutricionista> all() throws SQLException {
        List<Nutricionista> nutricionistas = new ArrayList<Nutricionista>();
        
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
        nutricionistas = (List) daoNutricionista.all(this.getSession());
        
        return nutricionistas;
    }
    
    public List<Nutricionista> search(String searchQuery) throws SQLException {
        List<Nutricionista> nutricionistas = new ArrayList<Nutricionista>();
        
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
        nutricionistas = (List) daoNutricionista.search(searchQuery);
        
        return nutricionistas;
    }
    
    public void create(Nutricionista nutricionista) throws SQLException {
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
        daoNutricionista.create(nutricionista);
    }
    
    public void createUsuario(Usuario usuario, int idDono) throws SQLException {
        usuario.setTipoUsuario(TipoUsuario.NUTRICIONISTA.getCod());
        usuario.setIdDono(idDono);
        Usuario.create(usuario, this.getSession(), this.getTransaction());
        
    }
    
    public void update(Nutricionista nutricionista) throws SQLException {
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
        daoNutricionista.update(nutricionista);
    }
    
    public void delete(Nutricionista nutricionista) throws SQLException {
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
        Usuario.delete(nutricionista.getId(), this.getSession(), this.getTransaction());
        daoNutricionista.delete(nutricionista);
    }
    
    private List<String> validate() {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();
                
        if (request.getParameter("nome").length() < 1) {
            errors.add("O campo nome deve ser preenchido;");
        }

        if (request.getParameter("cpf").length() < 1) {
            errors.add("O campo cpf deve ser preenchido;");
        } else {
            String cpf = request.getParameter("cpf");
            DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession(), this.getTransaction());
        
            if (daoNutricionista.checkExistance(cpf)) {
                errors.add("Já existe um nutricionista cadastrado com este cpf;");
            } else if (cpf.length() != 11) {
                errors.add("O campo cpf deve conter 11 digitos;");
            }
        }

        if (request.getParameter("crn").length() < 1) {
            errors.add("O campo crn deve ser preenchido;");
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
    
    private void validateCreate(List<String> errors) throws Exception {
        HttpServletRequest request = this.getRequest();
        
        String email = request.getParameter("email");
        if (email.length() < 1) {
            errors.add("O campo email deve ser preenchido;");
        } else {
        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession(), this.getTransaction());;
            
            if (daoUsuario.checkExistance(email)) {
                errors.add("Já existe um usuário cadastrado com este email;");
            }
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
    
    private Nutricionista processRequestForm() {
        Nutricionista nutri = new Nutricionista();
        HttpServletRequest request = this.getRequest();
        
        if (request.getParameter("id") != null) {
            nutri.setId(Integer.parseInt(request.getParameter("id")));
        }

        nutri.setNome(request.getParameter("nome"));
        nutri.setCpf(request.getParameter("cpf"));
        nutri.setCrn(request.getParameter("crn"));
        //Endereço
        nutri.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado") != null && request.getParameter("estado").length() > 0) {
            nutri.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        nutri.setCidade(request.getParameter("cidade"));
        nutri.setBairro(request.getParameter("bairro"));
        nutri.setRua(request.getParameter("rua"));
        
        if (request.getParameter("numeroEndereco").length() > 0) {
            nutri.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        nutri.setComplemento(request.getParameter("complemento"));
        //Telefone
        if (request.getParameter("numeroTelefone").length() > 0) {
            String telefone = request.getParameter("numeroTelefone");
            nutri.setCodigoAreaTelefone(Integer.parseInt(telefone.substring(0, 2)));
            nutri.setNumeroTelefone(telefone.substring(2));
        }
        
        if (request.getParameter("numeroCelular").length() > 0) {
            String celular = request.getParameter("numeroCelular");
            nutri.setCodigoAreaCelular(Integer.parseInt(celular.substring(0, 2)));
            nutri.setNumeroCelular(celular.substring(2));
        }

        return nutri;
    }

    private Nutricionista processRequestForError() {
        Nutricionista nutri = new Nutricionista();
        HttpServletRequest request = this.getRequest();
        
        if (request.getParameter("id") != null) {
            nutri.setId(Integer.parseInt(request.getParameter("id")));
        }

        nutri.setNome(request.getParameter("nome"));
        nutri.setCpf(request.getParameter("cpf"));
        nutri.setCrn(request.getParameter("crn"));
        //Endereço
        nutri.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado") != null && request.getParameter("estado").length() > 0) {
            nutri.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        nutri.setCidade(request.getParameter("cidade"));
        nutri.setBairro(request.getParameter("bairro"));
        nutri.setRua(request.getParameter("rua"));
        
        if (request.getParameter("numeroEndereco").length() > 0) {
            nutri.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        nutri.setComplemento(request.getParameter("complemento"));
        //Telefone
        nutri.setNumeroTelefone(request.getParameter("numeroTelefone"));
        nutri.setNumeroCelular(request.getParameter("numeroCelular"));

        return nutri;
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
            Logger.getLogger(NutricionistasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NutricionistasController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NutricionistasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NutricionistasController.class.getName()).log(Level.SEVERE, null, ex);
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
