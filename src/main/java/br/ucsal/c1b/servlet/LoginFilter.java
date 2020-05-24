package br.ucsal.c1b.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.c1b.vo.Usuario;


/**
 * Servlet Filter implementation class Seguranca
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/sistema/*")
public class LoginFilter implements Filter {


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//TODO
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Usuario user = (Usuario) httpServletRequest.getSession().getAttribute("usuarioLogado");

		if (user != null ) {
				chain.doFilter(httpServletRequest, httpServletResponse);
		}else {
		
			if( httpServletRequest.getRequestURI().endsWith("index.html") ||
					httpServletRequest.getRequestURI().endsWith("style.css") ||
					httpServletRequest.getRequestURI().endsWith("Login")) {
				chain.doFilter(request, response);

			}else {
				httpServletResponse.sendRedirect("../login.html");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}