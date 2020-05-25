package br.ucsal.c1b.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.c1b.dao.UsuarioDAO;
import br.ucsal.c1b.vo.Usuario;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("UTF-8");
		
		Usuario user = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		user.setLogin(request.getParameter("user"));
		user.setSenha(request.getParameter("senha"));
		
		if (user.getLogin() != null && !user.getLogin().trim().isEmpty() && user.getSenha() != null
				&& !user.getSenha().trim().isEmpty()) {
			try {

				if (dao.autentication(user)) {
					request.getSession().setAttribute("usuarioLogado", user);
						response.sendRedirect("sistema/index.html");
					
				} else {

					response.sendRedirect("./login.html");
				}

			} catch (SQLException e) {
				response.sendRedirect("./login.html");
				e.printStackTrace();
			}
		}
	}

}
