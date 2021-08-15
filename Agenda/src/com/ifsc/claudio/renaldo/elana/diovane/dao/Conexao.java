package com.ifsc.claudio.renaldo.elana.diovane.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String Login_Banco = "root";

	private static final String Senha_Banco = "191580";

	private static final String URL_Banco = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";

	public Connection getConnection() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(Conexao.URL_Banco, Conexao.Login_Banco, Conexao.Senha_Banco);

		} catch (SQLException e) {
			System.out.println("erro ao conectar:ERRO " + e);
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			System.out.println("NAO foi possivel carregar a classe jdbc:ERRO " + e);
		} catch (Exception e) {
			System.out.println("erro geral:ERRO " + e);
		}
		return conexao;
	}

}
