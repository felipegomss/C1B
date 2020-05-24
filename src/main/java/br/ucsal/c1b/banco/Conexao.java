package br.ucsal.c1b.banco;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao { 

	private static Connection connection;
	
	static { 
		try { 
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbC1B",
					"SA", ""); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		} 

	public static Connection getConnection() { 
		return connection; 
		} 
	}