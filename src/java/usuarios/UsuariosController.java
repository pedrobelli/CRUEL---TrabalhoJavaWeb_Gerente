package usuarios;

import gerentes.DaoGerente;
import gerentes.Gerente;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import utils.TipoUsuarioEnum;

@WebServlet(name = "UsuariosController", urlPatterns = {"/usuarios"})
public class UsuariosController extends HttpServlet {
    
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            this.setRequest(request);
            this.setResponse(response);
            this.setSession(HibernateUtil.getSessionFactory().openSession());
            this.setTransaction(this.getSession().beginTransaction());

            String action = request.getParameter("action");

            if (action.equals("login")) {
                this.validateLoginFields();
                this.login();
                
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/gerentes");
            } else if (action.equals("logout")) {
                this.logout();
            }

        } catch(Exception E) {
            String action = request.getParameter("action");

            if (action.equals("login")) {
                getServletContext().getRequestDispatcher("/gerente/login.jsp").forward(request, response);
            }
            
        }finally {
            out.close();
        }
    }
    
    public void login() throws IOException, NoSuchAlgorithmException, Exception {
        HttpServletRequest request = this.getRequest();
        
        Usuario usuario = new Usuario();
        String senha = request.getParameter("senha");
        
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(senha.getBytes("UTF-8"));
        
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(new BigInteger(1,messageDigest.digest()).toString(16));

        DaoUsuario daoUsuario = new DaoUsuario().setDaoUsuario(this.getSession());
        Usuario usuarioPersistido = (Usuario) daoUsuario.getByEmailESenha(usuario);
        
        if (usuarioPersistido.getEmail() != null && usuarioPersistido.getTipoUsuario() == TipoUsuarioEnum.TipoUsuario.GERENTE.getCod()) {
            DaoGerente daoGerente = new DaoGerente().setDaoGerente(this.getSession());
            Gerente gerente = daoGerente.get(usuarioPersistido.getIdDono());
            
            request.getSession().setAttribute("usuarioSession", usuarioPersistido);
            request.getSession().setAttribute("nomeDonoSession", gerente.getNome());
        } else {
            request.setAttribute("mensagem", "Usuário ou senha inválidos!");
            throw new Exception();
        }
    }
    
    public void logout() throws IOException{
        this.getRequest().getSession().removeAttribute("usuarioSession");
        this.getRequest().getSession().removeAttribute("nomeDonoSession");
        this.getResponse().sendRedirect(getServletContext().getContextPath() + "/login");
    }
    
    public void validateLoginFields() throws IOException, Exception {
        HttpServletRequest request = this.getRequest();
        
        String login = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (login.isEmpty() || senha.isEmpty()) {
            request.setAttribute("mensagem", "Os campos email e senha são necessário para realizar o login!");
            throw new Exception();
        }
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
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
