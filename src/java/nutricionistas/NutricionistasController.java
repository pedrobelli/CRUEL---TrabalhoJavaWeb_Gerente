package nutricionistas;
    
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
            
            HttpSession httpSession = request.getSession(false); 
            Usuario usuarioSession = (Usuario) httpSession.getAttribute("usuarioSession");

            if (usuarioSession == null) {
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/login");
            }

            String action = request.getParameter("action");

            if (action == null) {
                request.setAttribute("nutricionistas", this.all()); 

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);

            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");

                if (!searchQuery.equals("")) {
                    request.setAttribute("nutricionistas", this.search(searchQuery));
                } else {
                    request.setAttribute("nutricionistas", this.all());
                }

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);

            } else if (action.equals("new")) {
                request.setAttribute("nutricionista", new Nutricionista());

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/new.jsp").forward(request, response);

            } else if (action.equals("create")) {
                List<String> errors = this.validate();
                this.validateCreate(errors);

                Nutricionista nutricionista = this.processRequestForm();
                this.create(nutricionista);

                Usuario usuario = Usuario.processRequestFormCreate(request);
                this.createUsuario(usuario, nutricionista.getId());

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/nutricionistas");

            } else if (action.equals("edit")) {
                DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));
                
                DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());
                
                request.setAttribute("nutricionista", daoNutricionista.get(id));
                request.setAttribute("usuario", daoUsuario.getByOwnerEOwnerType(id, TipoUsuario.NUTRICIONISTA.getCod()));

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/edit.jsp").forward(request, response);

            } else if (action.equals("update")) {
                DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());
                int idDono = Integer.parseInt(request.getParameter("id"));
                Usuario usuario = (Usuario) daoUsuario.getByOwnerEOwnerType(idDono, TipoUsuario.NUTRICIONISTA.getCod());
                
                List<String> errors = this.validate();
                this.validateUpdate(errors, usuario);

                Nutricionista nutricionista = this.processRequestForm();
                this.update(nutricionista);                
                
                usuario = Usuario.processRequestFormUpdate(usuario, request);
                this.updateUsuario(usuario, nutricionista.getId());

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/nutricionistas");

            } else if (action.equals("delete")) {
                Nutricionista nutricionista = new Nutricionista();
                nutricionista.setId(Integer.parseInt(request.getParameter("id")));

                this.delete(nutricionista);

                this.getTransaction().commit();

               this.getResponse().sendRedirect(getServletContext().getContextPath() + "/nutricionistas");

            }
        } catch(Exception E) {
            E.printStackTrace();
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

        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
        nutricionistas = (List) daoNutricionista.all(this.getSession());

        return nutricionistas;
    }

    public List<Nutricionista> search(String searchQuery) throws SQLException {
        List<Nutricionista> nutricionistas = new ArrayList<Nutricionista>();

        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
        nutricionistas = (List) daoNutricionista.search(searchQuery);

        return nutricionistas;
    }

    public void create(Nutricionista nutricionista) throws SQLException {
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
        daoNutricionista.create(nutricionista);
    }

    public void createUsuario(Usuario usuario, int idDono) throws SQLException {
        usuario.setTipoUsuario(TipoUsuario.NUTRICIONISTA.getCod());
        usuario.setIdDono(idDono);
        Usuario.create(usuario, this.getSession());

    }

    public void update(Nutricionista nutricionista) throws SQLException {
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
        daoNutricionista.update(nutricionista);
    }
    
    public void updateUsuario(Usuario usuario, int idDono) throws SQLException {
        usuario.setTipoUsuario(TipoUsuario.NUTRICIONISTA.getCod());
        usuario.setIdDono(idDono);
        Usuario.update(usuario, this.getSession());
    }

    public void delete(Nutricionista nutricionista) throws SQLException {
        DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
        Usuario.delete(nutricionista.getId(), TipoUsuario.NUTRICIONISTA.getCod(),this.getSession());
        daoNutricionista.delete(nutricionista);
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
            DaoNutricionista daoNutricionista = new DaoNutricionista().setDaoNutricionista(this.getSession());
            
            String action = request.getParameter("action");
            
            if (action.equals("create")) {
                if (daoNutricionista.checkExistance(cpf)) {
                    errors.add("Já existe um nutricionista cadastrado com este cpf;");
                } else if (cpf.length() != 11) {
                    errors.add("O campo cpf deve conter 11 digitos;");
                }
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                Nutricionista nutri = daoNutricionista.get(id);
                
                if(!nutri.getCpf().equals(cpf)) {
                    errors.add("Já existe um nutricionista cadastrado com este cpf;");
                } else if (cpf.length() != 11) {
                    errors.add("O campo cpf deve conter 11 digitos;");
                }
            }
            
        }

        if (request.getParameter("crn").isEmpty()) {
            errors.add("O campo crn deve ser preenchido;");
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

        if (request.getParameter("estado") != null && !request.getParameter("estado").isEmpty()) {
            nutri.setEstado(Integer.parseInt(request.getParameter("estado")));
        }

        nutri.setCidade(request.getParameter("cidade"));
        nutri.setBairro(request.getParameter("bairro"));
        nutri.setRua(request.getParameter("rua"));

        if (!request.getParameter("numeroEndereco").isEmpty()) {
            nutri.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        }

        nutri.setComplemento(request.getParameter("complemento"));
        //Telefone
        if (!request.getParameter("numeroTelefone").isEmpty()) {
            String telefone = request.getParameter("numeroTelefone");
            nutri.setCodigoAreaTelefone(Integer.parseInt(telefone.substring(0, 2)));
            nutri.setNumeroTelefone(telefone.substring(2));
        }

        if (!request.getParameter("numeroCelular").isEmpty()) {
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

        if (request.getParameter("estado") != null && !request.getParameter("estado").isEmpty()) {
            nutri.setEstado(Integer.parseInt(request.getParameter("estado")));
        }

        nutri.setCidade(request.getParameter("cidade"));
        nutri.setBairro(request.getParameter("bairro"));
        nutri.setRua(request.getParameter("rua"));

        if (!request.getParameter("numeroEndereco").isEmpty()) {
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
