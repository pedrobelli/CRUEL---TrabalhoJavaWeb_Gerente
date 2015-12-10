package gerentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
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
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import usuarios.DaoUsuario;
import usuarios.Usuario;
import utils.HibernateUtil;
import utils.TipoUsuarioEnum.TipoUsuario;

@WebServlet(name = "GerentesController", urlPatterns = {"/gerentes"})
public class GerentesController extends HttpServlet {
    
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
            
            HttpSession httpSession = request.getSession(false); 
            Usuario usuarioSession = (Usuario) httpSession.getAttribute("usuarioSession");

            if (usuarioSession == null) {
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/login");
            }
            
            String action = request.getParameter("action");
            
            if (action == null) {
                
                request.setAttribute("gerentes", this.all());
                
                this.getTransaction().commit();
                 
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
          
            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");
                
                if (!searchQuery.equals("")) {
                    request.setAttribute("gerentes", this.search(searchQuery));
                } else {
                    request.setAttribute("gerentes", this.all());
                }
                
                this.getTransaction().commit();
                 
                getServletContext().getRequestDispatcher("/gerente/gerentes/index.jsp").forward(request, response);
                
            } else if (action.equals("new")) {
                
                request.setAttribute("gerente", new Gerente());
                
                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/gerente/gerentes/new.jsp").forward(request, response);
                
            } else if (action.equals("create")) {
                List<String> errors = this.validate();
                this.validateCreate(errors);
                
                Gerente gerente = this.processRequestForm();
                this.create(gerente);
                
                Usuario usuario = Usuario.processRequestFormCreate(request);
                this.createUsuario(usuario, gerente.getId());
                
                this.getTransaction().commit();
                
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/gerentes");
                
            } else if (action.equals("edit")) {
                DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));
                
                DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());
                
                request.setAttribute("gerente", daoGerente.get(id));
                request.setAttribute("usuario", daoUsuario.getByOwnerEOwnerType(id, TipoUsuario.GERENTE.getCod()));
                
                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/gerente/gerentes/edit.jsp").forward(request, response);
                
            } else if (action.equals("update")) {
                DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());
                int idDono = Integer.parseInt(request.getParameter("id"));
                Usuario usuario = (Usuario) daoUsuario.getByOwnerEOwnerType(idDono, TipoUsuario.GERENTE.getCod());
                
                List<String> errors = this.validate();
                this.validateUpdate(errors, usuario);
                
                Gerente gerente = this.processRequestForm();
                this.update(gerente);                
                
                usuario = Usuario.processRequestFormUpdate(usuario, request);
                this.updateUsuario(usuario, gerente.getId());
                
                this.getTransaction().commit();
                
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/gerentes");
                
            } else if (action.equals("delete")) {
                Gerente gerente = new Gerente();
                gerente.setId(Integer.parseInt(request.getParameter("id")));
                
                this.delete(gerente);
                
                this.getTransaction().commit();
                
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/gerentes");
            
            }
            
        } catch(Exception E) {
            request.setAttribute("gerente", this.processRequestForError());
            request.setAttribute("usuario", Usuario.processRequestForError(request));
            
            String action = request.getParameter("action");
            
            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/gerente/gerentes/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/gerente/gerentes/edit.jsp").forward(request, response);
            }
        }finally {
            out.close();
        }
    }
    
    public List<Gerente> all() throws SQLException {
        List<Gerente> gerentes = new ArrayList<Gerente>();
        
        DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
        gerentes = (List) daoGerente.all(this.getSession());
        
        return gerentes;
    }
    
    public List<Gerente> search(String searchQuery) throws SQLException {
        List<Gerente> gerentes = new ArrayList<Gerente>();
        
        DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
        gerentes = (List) daoGerente.search(searchQuery);
        
        return gerentes;
    }
    
    public void create(Gerente gerente) throws SQLException {
        DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
        daoGerente.create(gerente);
    }
    
    public void createUsuario(Usuario usuario, int idDono) throws SQLException {
        usuario.setTipoUsuario(TipoUsuario.GERENTE.getCod());
        usuario.setIdDono(idDono);
        Usuario.create(usuario, this.getSession());
        
    }
    
    public void update(Gerente gerente) throws SQLException {
        DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
        daoGerente.update(gerente);
    }
    
    public void updateUsuario(Usuario usuario, int idDono) throws SQLException {
        usuario.setTipoUsuario(TipoUsuario.GERENTE.getCod());
        usuario.setIdDono(idDono);
        Usuario.update(usuario, this.getSession());
    }
    
    public void delete(Gerente gerente) throws SQLException {
        DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
        Usuario.delete(gerente.getId(), TipoUsuario.GERENTE.getCod(), this.getSession());
        daoGerente.delete(gerente);
    }
    
    private List<String> validate() {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();
                
        if (request.getParameter("nome").isEmpty()) {
            errors.add("O campo nome deve ser preenchido;");
        }

        if (request.getParameter("cpf").isEmpty()) {
            errors.add("O campo cpf deve ser preenchido;");
        } else {
            String cpf = request.getParameter("cpf");
            DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
        
            String action = request.getParameter("action");
            
            if (action.equals("create")) {
                if (daoGerente.checkExistance(cpf)) {
                    errors.add("Já existe um nutricionista cadastrado com este cpf;");
                } else if (cpf.length() != 11) {
                    errors.add("O campo cpf deve conter 11 digitos;");
                }
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                Gerente geren = daoGerente.get(id);
                
                if(!geren.getCpf().equals(cpf)) {
                    errors.add("Já existe um nutricionista cadastrado com este cpf;");
                } else if (cpf.length() != 11) {
                    errors.add("O campo cpf deve conter 11 digitos;");
                }
            }
        }

        String cep = request.getParameter("cep");
        if (!cep.isEmpty() && cep.length() != 8) {
            errors.add("O campo cep deve conter 8 digitos;");
        }

        String numeroTelefone = request.getParameter("numeroTelefone");
        if (!numeroTelefone.isEmpty()) {
            if (numeroTelefone.length() < 10) {
                errors.add("O campo telefone deve conter no mínimo 10 digitos;");
            } else if (numeroTelefone.length() > 11) {
                errors.add("O campo telefone não pode conter mais de 11 digitos;");
            }
        }

        String numeroCelular = request.getParameter("numeroCelular");
        if (!numeroCelular.isEmpty()) {
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
        if (email.isEmpty()) {
            errors.add("O campo email deve ser preenchido;");
        } else {
        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());;
            
            if (daoUsuario.checkExistance(email)) {
                errors.add("Já existe um usuário cadastrado com este email;");
            }
        }
        
        String senha = request.getParameter("senha");
        String confirmSenha = request.getParameter("confirmSenha");
        if (senha.isEmpty() || confirmSenha.isEmpty()) {
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
    
    private void validateUpdate(List<String> errors, Usuario usuario) throws Exception {
        HttpServletRequest request = this.getRequest();

        String email = request.getParameter("email");
        if (email.isEmpty()) {
            errors.add("O campo email deve ser preenchido;");
        }

        String senhaAntiga = request.getParameter("senhaAntiga");
        String senha = request.getParameter("senha");
        String confirmSenha = request.getParameter("confirmSenha");
        
        if (!senhaAntiga.isEmpty()) {       
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(senhaAntiga.getBytes("UTF-8"));

            senhaAntiga = new BigInteger(1,messageDigest.digest()).toString(16);
            
            if (!usuario.getSenha().equals(senhaAntiga)) {
                errors.add("A senha informada não esta correta;");
            } else if (senha.isEmpty() || confirmSenha.isEmpty()) {
                errors.add("Os campo nova senha e confirma nova senha devem ser preenchidos;");
            } else {
                if (!senha.equals(confirmSenha)) {
                    errors.add("Os campo nova senha e confirma nova senha estão diferentes;");
                }
            }   
        }

        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
        }   
    }
    
    private Gerente processRequestForm() {
        Gerente geren = new Gerente();
        HttpServletRequest request = this.getRequest();
        
        if (request.getParameter("id") != null) {
            geren.setId(Integer.parseInt(request.getParameter("id")));
        }

        geren.setNome(request.getParameter("nome"));
        geren.setCpf(request.getParameter("cpf"));
        //Endereço
        geren.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado") != null && !request.getParameter("estado").isEmpty()) {
            geren.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        geren.setCidade(request.getParameter("cidade"));
        geren.setBairro(request.getParameter("bairro"));
        geren.setRua(request.getParameter("rua"));
        
        if (!request.getParameter("numeroEndereco").isEmpty()) {
            geren.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }
        
        geren.setComplemento(request.getParameter("complemento"));
        //Telefone
        if (!request.getParameter("numeroTelefone").isEmpty()) {
            String telefone = request.getParameter("numeroTelefone");
            geren.setCodigoAreaTelefone(Integer.parseInt(telefone.substring(0, 2)));
            geren.setNumeroTelefone(telefone.substring(2));
        }
        
        if (!request.getParameter("numeroCelular").isEmpty()) {
            String celular = request.getParameter("numeroCelular");
            geren.setCodigoAreaCelular(Integer.parseInt(celular.substring(0, 2)));
            geren.setNumeroCelular(celular.substring(2));
        }

        return geren;
    }

    private Gerente processRequestForError() {
        Gerente geren = new Gerente();
        HttpServletRequest request = this.getRequest();
        
        if (request.getParameter("id") != null) {
            geren.setId(Integer.parseInt(request.getParameter("id")));
        }

        geren.setNome(request.getParameter("nome"));
        geren.setCpf(request.getParameter("cpf"));
        //Endereço
        geren.setCep(request.getParameter("cep"));
        
        if (request.getParameter("estado") != null && !request.getParameter("estado").isEmpty()) {
            geren.setEstado(Integer.parseInt(request.getParameter("estado")));
        }
        
        geren.setCidade(request.getParameter("cidade"));
        geren.setBairro(request.getParameter("bairro"));
        geren.setRua(request.getParameter("rua"));
        
        if (!request.getParameter("numeroEndereco").isEmpty()) {
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
