package br.ucsal.c1b.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.c1b.dao.UsuarioDAO;
import br.ucsal.c1b.vo.Usuario;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/Cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CadastroServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();		
		Usuario user = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		user.setLogin(request.getParameter("user"));
		user.setSenha(request.getParameter("senha"));
		user.setBairro(request.getParameter("bairro"));
		user.setBarbeiro(request.getParameter("group").equalsIgnoreCase("Sim") ? true : false );
		if(user.isBarbeiro()) {
			user.setValorServico(request.getParameter("valor"));
		}
		user.setCep(request.getParameter("cep"));
		user.setCidade(request.getParameter("cidade"));
		user.setComplemento(request.getParameter("complemento"));
		user.setCpf(request.getParameter("cpf"));
		user.setEstado(request.getParameter("uf"));
		user.setNome(request.getParameter("nome"));
		user.setNumeroCasa(request.getParameter("num"));
		user.setRua(request.getParameter("rua"));
		user.setTelefone(request.getParameter("telefone"));
		String confirmation = request.getParameter("senhaConfirm");

		if (user.getSenha().equals(confirmation)) {

			if (user.getLogin() != null && !user.getLogin().trim().isEmpty() && user.getSenha() != null
					&& !user.getSenha().trim().isEmpty()) {
				try {

					if (dao.signUpAutentication(user)) {
						dao.insertUser(user);
						request.getRequestDispatcher("sistema/index.html").forward(request, response);
						
					} else {
						request.getRequestDispatcher("login.html").forward(request, response);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {

			out.print("error, senhas nao coincidem");
		}

	}

}
