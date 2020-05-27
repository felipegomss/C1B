package br.ucsal.c1b.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.c1b.banco.Conexao;
import br.ucsal.c1b.vo.Notificacao;
import br.ucsal.c1b.vo.Usuario;

public class NotificacaoDAO {
	
	public void hairCutFinish (Notificacao notificacao) {
		Connection con = Conexao.getConnection();

		try {
			String query = "update notificacao set isSERVICOFEITO = 'true' where id_notificacao = " + notificacao.getIdNotificacao();

			Statement stm = con.createStatement();
			stm.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insertAppointment(Usuario cliente, Usuario barbeiro, Notificacao notificacao) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			String query = "INSERT INTO NOTIFICACAO " + "(ID_NOTIFICACAO, " + "CLIENTE_ID, " + "BARBEIRO_ID, "
					+ "MENSAGEM, " + "DATA_MARCADA, " + "isSERVICOFEITO) " + "VALUES (DEFAULT, " + cliente.getId()
					+ ", " + barbeiro.getId() + ", '" + notificacao.getMensagem() + "', '"
					+ notificacao.getDataMarcada() + "', 'false')";

			Statement stm = con.createStatement();
			stm.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cancelHairCut(Notificacao notificacao) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			String query = "delete from notificacao where id_notificacao = " + notificacao.getIdNotificacao();

			Statement stm = con.createStatement();
			stm.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Notificacao> showNotification(Usuario user) throws SQLException {
		Connection con = Conexao.getConnection();
		
		List<Notificacao> notificationList = new ArrayList<Notificacao>();
		
		try {
			String query = "";
			
			if (user.isBarbeiro()) {
				query = "select mensagem from notificacao where barbeiro.id = " + user.getId()
						+ " and isSERVICOFEITO = 'false'";
			} else {
				query = "select mensagem from notificacao where cliente.id = " + user.getId()
						+ " and isSERVICOFEITO = 'false'";
			}
			
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(query);
			
			while (result.next()) {
				Notificacao notificacao = new Notificacao();
				notificacao.setIdNotificacao(result.getInt("ID_NOTIFICACAO"));
				notificacao.setDataMarcada(result.getString("DATA_MARCADA"));
				notificacao.setIdBarbeiro(result.getInt("BARBEIRO_ID"));
				notificacao.setIdCliente(result.getInt("CLIENTE_ID"));
				notificacao.setMensagem(result.getString("MENSAGEM"));

				notificationList.add(notificacao);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return notificationList;
	}

}
