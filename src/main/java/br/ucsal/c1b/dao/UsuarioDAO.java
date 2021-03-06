package br.ucsal.c1b.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.c1b.banco.Conexao;
import br.ucsal.c1b.vo.Usuario;

public class UsuarioDAO {
	
    List<Usuario> listaBarbeiros = new ArrayList<Usuario>();
 

	public boolean autentication(Usuario user) throws SQLException {
		Connection con = Conexao.getConnection();

		try {
			String query = "SELECT * FROM USUARIO WHERE LOGIN = '" + user.getLogin() + "' AND SENHA = '"
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
			String query = "INSERT INTO USUARIO( " + "ID, " + "LOGIN, " + "SENHA, " + "NOME, " + "isBARBEIRO, "
					+ "TELEFONE, " + "CEP, " + "ESTADO, " + "CIDADE, " + "BAIRRO, " + "RUA, " + "NUMEROCASA, "
					+ "COMPLEMENTO, " + "CPF, " + "VALOR)" + "VALUES " + "(DEFAULT, " + "'" + user.getLogin() + "', "
					+ "'" + user.getSenha() + "', " + "'" + user.getNome() + "', " + "'" + user.isBarbeiro() + "', "
					+ "'" + user.getTelefone() + "', " + "'" + user.getCep() + "', " + "'" + user.getEstado() + "', "
					+ "'" + user.getCidade() + "', " + "'" + user.getBairro() + "', " + "'" + user.getRua() + "', "
					+ "'" + user.getNumeroCasa() + "', " + "'" + user.getComplemento() + "', " + "'" + user.getCpf()
					+ "', " + "'" + user.getValorServico() + "')";
			Statement stm = con.createStatement();
			stm.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Usuario> listBarber(Usuario user) throws SQLException {

		Connection con = Conexao.getConnection();

		try {
			String query = "SELECT * FROM USUARIO WHERE CIDADE = '" + user.getCidade()
					+ "' and isbarbeiro = 'true' ORDER BY BAIRRO";
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(query);

			while (result.next()) {
				Usuario userAux = new Usuario();
				userAux.setId(result.getInt("ID"));
				userAux.setLogin(result.getString("LOGIN"));
				userAux.setSenha(result.getString("SENHA"));
				userAux.setBairro(result.getString("BAIRRO"));
				userAux.setBarbeiro(result.getString("ISBARBEIRO").equalsIgnoreCase("true") ? true : false);
				if (userAux.isBarbeiro()) {
					userAux.setValorServico(result.getString("VALOR"));
				}
				userAux.setCep(result.getString("CEP"));
				userAux.setCidade(result.getString("CIDADE"));
				userAux.setComplemento(result.getString("COMPLEMENTO"));
				userAux.setCpf(result.getString("CPF"));
				userAux.setEstado(result.getString("ESTADO"));
				userAux.setNome(result.getString("NOME"));
				userAux.setNumeroCasa(result.getString("NUMEROCASA"));
				userAux.setRua(result.getString("RUA"));
				userAux.setTelefone(result.getString("TELEFONE"));

				listaBarbeiros.add(userAux);
			}
			return listaBarbeiros;

		} catch (Exception e) {
			throw new SQLException("Erro ao listar barbeiros");
		}

	}

	public Usuario userInfo(int id) {

		Connection con = Conexao.getConnection();
		Usuario user = new Usuario();

		try {
			String query = "SELECT * FROM USUARIO WHERE ID = " + id;
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(query);

			if (result.next()) {
				user.setId(result.getInt("ID"));
				user.setLogin(result.getString("LOGIN"));
				user.setSenha(result.getString("SENHA"));
				user.setBairro(result.getString("BAIRRO"));
				user.setBarbeiro(result.getString("ISBARBEIRO").equalsIgnoreCase("true") ? true : false);
				if (user.isBarbeiro()) {
					user.setValorServico(result.getString("VALOR"));
				}
				user.setCep(result.getString("CEP"));
				user.setCidade(result.getString("CIDADE"));
				user.setComplemento(result.getString("COMPLEMENTO"));
				user.setCpf(result.getString("CPF"));
				user.setEstado(result.getString("ESTADO"));
				user.setNome(result.getString("NOME"));
				user.setNumeroCasa(result.getString("NUMEROCASA"));
				user.setRua(result.getString("RUA"));
				user.setTelefone(result.getString("TELEFONE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	public List<Usuario> getListaBarbeiros() {
		return listaBarbeiros;
	}

	public void setListaBarbeiros(List<Usuario> listaBarbeiros) {
		this.listaBarbeiros = listaBarbeiros;
	}
}
