package tiposCliente;
    
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
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
import usuarios.Usuario;
import utils.HibernateUtil;
    
@WebServlet(name = "TiposClienteController", urlPatterns = {"/tiposCliente"})
public class TiposClienteController extends HttpServlet {

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
                request.setAttribute("tiposCliente", this.all()); 

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);

            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");

                if (!searchQuery.equals("")) {
                    request.setAttribute("tiposCliente", this.search(searchQuery));
                } else {
                    request.setAttribute("tiposCliente", this.all());
                }

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/gerente/tiposCliente/index.jsp").forward(request, response);

            } else if (action.equals("new")) {
                request.setAttribute("tipoCliente", new TipoCliente());

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/gerente/tiposCliente/new.jsp").forward(request, response);

            } else if (action.equals("create")) {                    
                this.validate();

                TipoCliente tipoCliente = this.processRequestForm();
                this.create(tipoCliente);

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/tiposCliente");

            } else if (action.equals("edit")) {
                DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("tipoCliente", daoTipoCliente.get(id));

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/gerente/tiposCliente/edit.jsp").forward(request, response);

            } else if (action.equals("update")) {
                this.validate();

                TipoCliente tipoCliente = this.processRequestForm();
                this.update(tipoCliente);
                
                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/tiposCliente");

            } else if (action.equals("delete")) {
                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setId(Integer.parseInt(request.getParameter("id")));

                this.delete(tipoCliente);

                this.getTransaction().commit();

               this.getResponse().sendRedirect(getServletContext().getContextPath() + "/tiposCliente");

            }

        } catch(Exception E) {
            E.printStackTrace();
            request.setAttribute("tipoCliente", this.processRequestForError());
            request.setAttribute("usuario", Usuario.processRequestForError(request));

            String action = request.getParameter("action");

            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/gerente/tiposCliente/edit.jsp").forward(request, response);
            }
        }finally {
            out.close();
        }
    }

    public List<TipoCliente> all() throws SQLException {
        List<TipoCliente> tiposCliente = new ArrayList<TipoCliente>();

        DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
        tiposCliente = (List) daoTipoCliente.all(this.getSession());

        return tiposCliente;
    }

    public List<TipoCliente> search(String searchQuery) throws SQLException {
        List<TipoCliente> tiposCliente = new ArrayList<TipoCliente>();

        DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
        tiposCliente = (List) daoTipoCliente.search(searchQuery);

        return tiposCliente;
    }

    public void create(TipoCliente tipoCliente) throws SQLException {
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
        daoTipoCliente.create(tipoCliente);
    }

    public void update(TipoCliente tipoCliente) throws SQLException {
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
        daoTipoCliente.update(tipoCliente);
    }

    public void delete(TipoCliente tipoCliente) throws SQLException {
        DaoTipoCliente daoTipoCliente = new DaoTipoCliente().setDaoTipoCliente(this.getSession());
        daoTipoCliente.delete(tipoCliente);
    }

    private void validate() throws Exception {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("nome").isEmpty()) {
            errors.add("O campo nome deve ser preenchido;");
        }

        if (request.getParameter("valorRefeicao").isEmpty()) {
            errors.add("O campo crn deve ser preenchido;");
        } 

        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
         }   
    }

    private TipoCliente processRequestForm() {
        TipoCliente tipo = new TipoCliente();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            tipo.setId(Integer.parseInt(request.getParameter("id")));
        }

        tipo.setNome(request.getParameter("nome"));
        tipo.setValorRefeicao(parseDouble(request.getParameter("valorRefeicao")));

        return tipo;
    }

    private TipoCliente processRequestForError() {
        TipoCliente tipo = new TipoCliente();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            tipo.setId(Integer.parseInt(request.getParameter("id")));
        }

        tipo.setNome(request.getParameter("nome"));
        tipo.setValorRefeicao(parseDouble(request.getParameter("valorRefeicao")));

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
