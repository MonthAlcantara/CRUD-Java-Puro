package br.com.monthalcantara.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import br.com.monthalcantara.model.Cliente;
import br.com.monthalcantara.service.ClienteService;

@WebServlet({ "/cliente", "/clienteServlet", "/clienteControlle" })
public class ClienteServlet extends HttpServlet {

	ClienteService cliente = new ClienteService();
	String email = null;
	String nome = null;
	String login = null;
	String senha = null;

	public ClienteServlet() {
		System.out.println("Construindo Servlet....");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Inicializado");
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Chamando Service");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cliente cli = null;
		String id = req.getParameter("id");
		String acao = req.getParameter("acao");
		String acao2 = req.getParameter("acao2");
		int id2;
		if (id != null && id != "" && acao != null && acao != "") {
			if (acao.equals("esc")) {
				cliente.excluir(Integer.parseInt(id));
				System.out.println("Entrou em excluir");
			} else if (acao.equals("edit")) {
				System.out.println("Entrou no Editar");
				cli = ClienteService.buscaPorId(Integer.parseInt(id));

				req.setAttribute("cli", cli);

			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
			req.setAttribute("cli", cli);
			req.setAttribute("lista", cliente.getLista());
			dispatcher.forward(req, resp);
			Cliente cli2 = (Cliente) req.getAttribute("cliEditado");

		}
		if (acao2 != null && acao2.equals("salva")) {

			req.setAttribute("vazio", "ok");
			email = req.getParameter("email");
			nome = req.getParameter("nome");
			login = req.getParameter("login");
			senha = req.getParameter("senha");
			String i = (String) req.getParameter("i");

			if (i != null && i != "") {
				int novoI = Integer.parseInt(i);
				if (email == null || email == "" || nome == null || nome == "" || login == null || login == ""
						|| senha == null || senha == "") {
					req.setAttribute("vazio", null);

				} else {
					cli = new Cliente();
					System.out.println("Entrou no substituir");
					cli.setEmail(email);
					cli.setLogin(login);
					cli.setNome(nome);
					cli.setSenha(senha);
					cli.setId(novoI);
					ClienteService.editarPorId(novoI, cli);
					System.out.println("Entrou no salvar");
					req.setAttribute("vazio", "ok");
				}
				RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
				req.setAttribute("cli", null);
				req.setAttribute("lista", cliente.getLista());
				dispatcher.forward(req, resp);
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("cli", cli);
		req.setAttribute("lista", cliente.getLista());
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		email = req.getParameter("email");
		nome = req.getParameter("nome");
		login = req.getParameter("login");
		senha = req.getParameter("senha");

		Cliente cli = new Cliente();

		if (email == null || email == "" && nome == null || nome == "" || login == null || login == "" || senha == null
				|| senha == "") {
			req.setAttribute("vazio", null);

		} else {
			cli.setEmail(email);
			cli.setLogin(login);
			cli.setNome(nome);
			cli.setSenha(senha);

			cliente.cadastrar(cli);
			req.setAttribute("vazio", "ok");
		}
		String index = req.getParameter("index");
		int ind = 0;
		if (index != null) {
			ind = Integer.parseInt(index);
			cliente.excluir(ind);
			cli.setEmail(email);
			cli.setLogin(login);
			cli.setNome(nome);
			cli.setSenha(senha);
			cliente.excluir(ind);
			cliente.getLista().add(ind, cli);
			resp.sendRedirect("cliente");
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("lista", cliente.getLista());
		dispatcher.forward(req, resp);
		System.out.println(ind);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	public void destroy() {
		System.out.println("Servlet Será destruído");
		super.destroy();
	}
}
