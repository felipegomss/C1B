package br.ucsal.c1b.bo;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.c1b.dao.NotificacaoDAO;
import br.ucsal.c1b.dao.UsuarioDAO;
import br.ucsal.c1b.vo.Notificacao;
import br.ucsal.c1b.vo.Usuario;

public class Notificacoes {

    List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();

	public List<Notificacao> showNotification(Usuario user) {

		try {
		    
			NotificacaoDAO dao = new NotificacaoDAO();
			listaNotificacao = dao.showNotification(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaNotificacao;
	}

	public void cancelHairCut(Notificacao notificacao) {

		try {
			NotificacaoDAO dao = new NotificacaoDAO();
			dao.cancelHairCut(notificacao);

		} catch (Exception e) {

		}

	}

	public void generateAppointment(Usuario cliente, Usuario barbeiro, Notificacao notificacao) {

		try {
			NotificacaoDAO dao = new NotificacaoDAO();
			UsuarioDAO userDao = new UsuarioDAO();
			barbeiro = userDao.userInfo(barbeiro.getId());
			cliente = userDao.userInfo(cliente.getId());
			String mensagem = "Você tem um corte no dia " + notificacao.getDataMarcada();
			notificacao.setMensagem(mensagem);
			dao.insertAppointment(cliente, barbeiro, notificacao);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Notificacao> getListaNotificacao() {
		return listaNotificacao;
	}

	public void setListaNotificacao(List<Notificacao> listaNotificacao) {
		this.listaNotificacao = listaNotificacao;
	}

}
