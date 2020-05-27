package br.ucsal.c1b.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.c1b.bo.Notificacoes;
import br.ucsal.c1b.vo.Notificacao;
import br.ucsal.c1b.vo.Usuario;

/**
 * Servlet implementation class AgendarCorteServlet
 */
@WebServlet("/AgendarCorte")
public class AgendarCorteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendarCorteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Usuario user = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Usuario barbeiro = new Usuario();
		barbeiro.setId(Integer.parseInt(request.getParameter("barbeiroId")));
		Notificacao notificacao = new Notificacao();
		notificacao.setDataMarcada(request.getParameter("horario"));
		Notificacoes bo = new Notificacoes();
		
		if(user != null && notificacao != null && barbeiro != null) {
			
			try {
				bo.generateAppointment(user, barbeiro, notificacao);
				out.print("<script>alert('Agendado com sucesso');</script>");
				response.sendRedirect("sistema/index.html");
				
			} catch (Exception e) {
				out.print("<script>alert('Erro ao agendar');</script>");
				e.printStackTrace();
			}
			
		}
		
		
	}

}
