package usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuariosController", urlPatterns = {"/usuario"})
public class UsuariosController extends HttpServlet {
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            this.setRequest(request);
            this.setResponse(response);

            String op = request.getParameter("operacao");

            if (op.equals("login")) {
                this.login();
            }

            if (op.equals("index")) {
                /*this.index();*/
            }
            if (op.equals("logoff")) {
                /*this.logoff();*/
            }

        } finally {
            out.close();
        }
    }
    
    public void login() {

        /*String usuario = this.getRequest().getParameter("usuario").toString();
        String senha = this.getRequest().getParameter("senha").toString();


        if (usuario.isEmpty() || senha.isEmpty()) {
            request.setAttribute("mensagem", "Campos em branco");
            this.redirectTo("/index");
        }*/
        /*
         * FAZER VERIFICACAO DE USUARIO
         */
        /*Usuario user = new Usuario();

        user.setLogin(login);
        user.setSenha(passwd);

        if (login.equals("admin") && passwd.equals("admin")) {
            //vai redirecionar pra admin
            user.setNome(login);
            
            request.getSession().setAttribute("usuarioSession", user);
            //
            this.redirectTo("/admin?operacao=index");
        } else {

            DaoUsuario dbu = new DaoUsuario();
            try {
                Usuario logUser = (Usuario) dbu.consulta(user);
                request.getSession().setAttribute("usuarioSession", logUser);
                this.redirectTo("/evento?operacao=listar");

            } catch (Exception e) {
                request.setAttribute("mensagem", "Usuário ou senha inválidos");
                this.redirectTo("/index");
            }

        }*/
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
        processRequest(request, response);
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
        processRequest(request, response);
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
