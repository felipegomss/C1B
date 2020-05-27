package br.ucsal.c1b.bo;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.c1b.dao.NotificacaoDAO;
import br.ucsal.c1b.dao.UsuarioDAO;
import br.ucsal.c1b.vo.Notificacao;
import br.ucsal.c1b.vo.Usuario;

public class Notificacoes {

	public List<Notificacao> showNotification(Usuario user) {

		List<Notificacao> notificacao = new ArrayList<Notificacao>();
		try {

			NotificacaoDAO dao = new NotificacaoDAO();
			notificacao = dao.showNotification(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return notificacao;
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
			barbeiro = userDao.userInfo(barbeiro);
			cliente = userDao.userInfo(cliente);
			String mensagem = "Você tem um corte no dia " + notificacao.getDataMarcada() + ", " + 
					"cliente: " + cliente.getNome() + "/nbarbeiro: "
					+ barbeiro.getNome();
			notificacao.setMensagem(mensagem);
			dao.insertAppointment(cliente, barbeiro, notificacao);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
