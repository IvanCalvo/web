package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarroDAO;
import dao.PropostaDAO;
import dao.ClienteDAO;
import domain.Carro;
import domain.Cliente;
import domain.Proposta;
import domain.Usuario;
import util.Erro;

@WebServlet(urlPatterns = "/propostas/*")

public class PropostaController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    private PropostaDAO dao;
    private CarroDAO daoCarro;
    private ClienteDAO daoCliente;

    @Override
    public void init() {
        dao = new PropostaDAO();
        daoCarro = new CarroDAO();
        daoCliente = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                case "/lista":
                    lista(request, response);
                    break;
                case "/listaPorLoja":
                    listaDaLoja(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	
        List<Proposta> listaProposta = dao.getbyID_usuario(usuario.getId());
        request.setAttribute("listaProposta", listaProposta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaDaLoja(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	
        List<Proposta> listaProposta = dao.getbyID_loja(usuario.getId());
        request.setAttribute("listaProposta", listaProposta);
        request.setAttribute("Usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Long id_carro = Long.parseLong(request.getParameter("id"));
    	Carro carro = daoCarro.get(id_carro);
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Cliente cliente = daoCliente.get(usuario.getId());
    	
    	Proposta proposta = new Proposta(cliente, carro);
    	
    	if(!dao.checkProposta(proposta)) {
        	Erro erros = new Erro();
        	erros.add("Você já tem uma proposta pendente para este carro!");

    		request.setAttribute("mensagens", erros);
        }
    	
    	request.setAttribute("proposta", proposta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Proposta proposta = dao.get(id);
        request.setAttribute("proposta", proposta);
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        request.setAttribute("Usuario", usuario);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
    	Float valor = Float.parseFloat(request.getParameter("valor"));
        String modelo = request.getParameter("condPagamento");
    	LocalDate dataAtual = LocalDate.now();
        
        Long id_cliente = Long.parseLong(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDAO().get(id_cliente);
        
        Long id_carro = Long.parseLong(request.getParameter("idCarro"));
        Carro carro = new CarroDAO().get(id_carro);
        Proposta proposta = new Proposta(valor, modelo, dataAtual, cliente, carro);
        
        dao.insert(proposta);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Long proposta_id = Long.parseLong(request.getParameter("id"));
    	Float valor = Float.parseFloat(request.getParameter("valor"));
        String modelo = request.getParameter("condPagamento");
        LocalDate dataAtual = LocalDate.now();
    	String status = request.getParameter("status");
        
        Long id_cliente = Long.parseLong(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDAO().get(id_cliente);
        
        Long id_carro = Long.parseLong(request.getParameter("idCarro"));
        Carro carro = new CarroDAO().get(id_carro);
        Proposta proposta = new Proposta(proposta_id, valor, modelo, dataAtual, status, cliente, carro);
        dao.update(proposta);
        
        response.sendRedirect("listaPorLoja");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long proposta_id = Long.parseLong(request.getParameter("id"));

        Proposta proposta = new Proposta(proposta_id);
        dao.delete(proposta);
        response.sendRedirect("lista");
    }
}