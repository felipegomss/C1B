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
		
		//TODO
		Usuario user = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		user.setLogin(request.getParameter("username"));
		user.setSenha(request.getParameter("userPassword"));
		user.setBarbeiro((user.getLogin().equals("1") ? true : false));

		if (user.getLogin() != null && !user.getLogin().isEmpty() && user.getSenha() != null
				&& !user.getSenha().isEmpty()) {
			try {

				if (dao.autentication(user)) {
					request.getSession().setAttribute("usuarioLogado", user);
					if (!user.isBarbeiro()) {
						response.sendRedirect("sistema/dashboard.xhtml");
					} else {
						response.sendRedirect("sistema/admin.jsp");
					}
				} else {

					response.sendRedirect("./index.html");
				}

			} catch (SQLException e) {
				response.sendRedirect("./index.html");
				e.printStackTrace();
			}
		}
	}

}
