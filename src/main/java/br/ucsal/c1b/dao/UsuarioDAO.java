package br.ucsal.c1b.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ucsal.c1b.banco.Conexao;
import br.ucsal.c1b.vo.Usuario;

public class UsuarioDAO {
	
	public boolean autentication(Usuario user) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			String query = "SELECT * FROM USUARIO WHERE LOGIN = '" + user.getLogin() + "' and senha = '"
					+ user.getSenha() + "'";
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(query);

			if (result.next()) {
				user.setId(result.getInt("ID"));
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new SQLException("Senha ou usuario incorretos");
		}

	}

	public boolean signUpAutentication(Usuario user) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			String query = "SELECT * FROM USUARIO WHERE LOGIN = '" + user.getLogin() + "'";
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(query);

			if (result.next()) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			throw new SQLException("Nome de usuario ja em uso");
		}

	}

	public void insertUser(Usuario user) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			//TODO
			String query = "insert into usuario(id, login, senha, papel) values (default, '" + user.getLogin() + "', '"
					+ user.getSenha() + "', 2)";
			Statement stm = con.createStatement();
			stm.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void changePassword(Usuario user, String newPw) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			
			String query = "update usuario set senha = '" + newPw + "' where login = '"
					+ user.getLogin() + "'";
			
			Statement stm = con.createStatement();
			stm.executeQuery(query);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
