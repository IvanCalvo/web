package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarroDAO;
import dao.PropostaDAO;
import domain.Carro;
import domain.Proposta;
import domain.Usuario;
import util.Erro;

@WebServlet(urlPatterns = "/usuario/*")

public class UsuarioController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CarroDAO daoCarro;
    private PropostaDAO daoProposta;

    @Override
    public void init() {
        daoCarro = new CarroDAO();
        daoProposta = new PropostaDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
    	
    	if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("CLIENTE")) {
    		String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }

            try {
                switch (action) {
                    case "/listaProposta":
                        listaProposta(request, response);
                        break;
                    case "/listaCarros":
                        listaCarros(request, response);
                        break;
                    default:
                    	RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuario/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                    	
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [USER] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}    	
    }
    
    private void listaCarros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Carro> listaCarros = daoCarro.getAll();
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	request.setAttribute("Usuario", usuario);
        request.setAttribute("listaCarros", listaCarros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/carros/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaProposta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        List<Proposta> listaProposta = daoProposta.getbyID_usuario(usuario.getId());
        request.setAttribute("listaProposta", listaProposta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/lista.jsp");
        dispatcher.forward(request, response);
    }
}