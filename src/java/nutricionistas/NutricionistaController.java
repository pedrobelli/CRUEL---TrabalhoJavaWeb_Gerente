package nutricionistas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NutricionistaController", urlPatterns = {"/nutricionistas"})
public class NutricionistaController extends HttpServlet {
    
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            this.setRequest(request);
            this.setResponse(response);
            
            String action = request.getParameter("action");
            
            if (action == null) {
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
            } else if (action.equals("new")) {
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/new.jsp").forward(request, response);
            } else if (action.equals("create")) {
                this.create(request);
                getServletContext().getRequestDispatcher("/gerente/nutricionistas/index.jsp").forward(request, response);
            }
            
        } finally {
            out.close();
        }
    }
    
    public void create(HttpServletRequest request) throws SQLException {
        Nutricionista nutricionista = this.processRequest(request);
        DaoNutricionista daoNutricionista = new DaoNutricionista();

        /*Frequencia FrequenciaUsuario = this.processaFrequencia(req);*/

        daoNutricionista.create(nutricionista);
        /*DaoUsuario atualizaUsuario = new DaoUsuario();
        Usuario modificado = (Usuario) atualizaUsuario.altera(frequenciaCadastrada.getUsuario());*/

    }
    
    private Nutricionista processRequest(HttpServletRequest request) {
        Nutricionista nutri = new Nutricionista();
        
        if (request.getParameter("id") != null) {
            nutri.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        nutri.setNome(request.getParameter("nome"));
        nutri.setCpf(request.getParameter("cpf"));
        nutri.setCrn(request.getParameter("crn"));
        //Endereço
        /*nutri.setCep(request.getParameter("cep"));
        nutri.setEstado(Integer.parseInt(request.getParameter("estado")));
        nutri.setCidade(request.getParameter("cidade"));
        nutri.setBairro(request.getParameter("bairro"));
        nutri.setRua(request.getParameter("rua"));
        nutri.setNumeroEndereco(Integer.parseInt(request.getParameter("numeroEndereco")));
        nutri.setComplemento(request.getParameter("complemento"));
        //Telefone
        nutri.setCodigoAreaCelular(Integer.parseInt(request.getParameter("codigoAreaTelefone")));
        nutri.setNumeroTelefone(request.getParameter("numeroTelefone"));
        nutri.setCodigoAreaCelular(Integer.parseInt(request.getParameter("codigoAreaCelular")));
        nutri.setNumeroTelefone(request.getParameter("numeroCelular"));*/

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
            Logger.getLogger(NutricionistaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NutricionistaController.class.getName()).log(Level.SEVERE, null, ex);
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
