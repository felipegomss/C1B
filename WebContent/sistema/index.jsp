<%@page import="br.ucsal.c1b.vo.Usuario"%>
<%@page import="br.ucsal.c1b.vo.Notificacao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <jsp:useBean id="usuarioDAO"
	class="br.ucsal.c1b.dao.UsuarioDAO" />
<jsp:useBean id="notificacaoDAO"
	class="br.ucsal.c1b.dao.NotificacaoDAO" />
	<jsp:useBean id="bo"
	class="br.ucsal.c1b.bo.Notificacoes" />
	
    <%
    Usuario auxiliar = new Usuario();
   Usuario user = (Usuario) request.getSession().getAttribute("usuarioLogado");
	
   user = usuarioDAO.userInfo(user.getId());
    
   bo.showNotification(user);
   usuarioDAO.listBarber(user);
    
    %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
<script src="../test.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Teko:wght@400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/86530e25de.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../main.css">
<title>BarbeLivery</title>

<script type="text/javascript">
</script>
</head>
<body>	
<div class="popUp-notificacao">
	<div>
		<output id="texto" name="texto">corte</output>	
	</div>
	<div class="bts">
	<button type="submit" value="cancelarCorte" id="cancelarCorte" class="cancelCorteBTN">Cancelar Corte</button>
	<button type="submit" value="fecharTela" id="fecharTela" class="cancelCorteBTN">Fechar</button>
</div>
	</div >
	<nav class="top-bar">
		<a href="index.html">
			<h1>BarbeLivery</h1>
		</a> <a href="#agendar">
			<h2>Agendar</h2>
		</a>
		<div class="simbolos">
			<i class="fas fa-bell fa-1x">
				<div class="notificacao">
					<table>
					<%for(Notificacao not : bo.getListaNotificacao()){%>
						<tr>
							 <td>
							 <input type="hidden" name="not" value="<%=user.isBarbeiro() == true ? not.getIdCliente() : not.getIdBarbeiro()%>">
							 	<a href="#" class="ntfcc"> <%=not.getMensagem()%>
								  <br> </a>
								 </td>
						</tr>
						<%} %>
						</table>
				</div>
			</i> <a href="../Logout"> <i class="fas fa-sign-out-alt"></i>
			</a>

		</div>
	</nav>

	<div class="banner">
		<h1>
			ta precisando de um corte <br> mas não pode sair de casa?
		</h1>
		<p>Agende agora e um barbeiro irá até você na sua própria casa!</p>
	</div>

	<div class="agendar" id="agendar">
		<div class="tit-table">
			<h2>Escolha seu horário</h2>
		</div>
		<form method="post" action="../AgendarCorte">
			<div class="tela-agendar" id="#agendamento">

				<label for="horario">Escolha o dia e o horário</label> <input
					type="date" id="dia" name="dia"> <input type="time"
					id="horas" name="horas">
				<div class="bts">
					<input type="submit" value="Agendar" class="buttonAgend"> <input
						type="submit" value="Cancelar" class="buttonCancel">
				</div>
		</div>
			<table>
				<thead>
					<tr>
						<td>Barbeiro</td>
						<td>Valor</td>
						<td>Agendar</td>
					</tr>
				</thead>
				<tbody>
				<%for(Usuario list : usuarioDAO.getListaBarbeiros()){%>
					<tr>
						<td><%=list.getNome()%></td>
						<td><%=list.getValorServico() %></td>
						<td><input type="radio" name="idBarbeiro" id="" value="<%=list.getId()%>"
							checked></td>
					</tr>
				</tbody>
				<%} %>
			</table>
			<div class="divBtnForm">
				<label value="AbrirPopUp" class="btnPopUp" id="btnPopUp">Agendar</label>
			</div>
		</form>
	</div>

	<footer>
		<p>
			Site desenvolvido com finalidade de obtenção de nota nas matéria de
			Teste e Qualidade de Software e Processos de Software <br>
			Fernando Gomes <br> Luis Felipe <br> Marcony Souza <br>
			Rodrigo Silvestre
		</p>
	</footer>

</body>

</html>