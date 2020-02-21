package br.com.monthalcantara.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	public static Connection con = null;
	public static PreparedStatement preparador = null;
	public static ResultSet resultado = null;

	public static Connection getConnection() {

		String sql = "jdbc:postgresql://localhost:5432/bancocjweb1";
		String user = "postgres";
		String senha = "admin";
		try {
			Class.forName("br.com.postgresql");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Erro: " + e);
		}

		try {
			con = DriverManager.getConnection(sql, user, senha);
			System.out.println("Conectou ao Banco");
		} catch (SQLException e) {
			System.err.println("Erro: " + e);
		}
		return con;
	}

	public static void fecharConexao(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Erro: " + e);
			}
		}
	}

	public static void fecharConexao(Connection con, PreparedStatement preparador) {
		if (preparador != null) {
			try {
				preparador.close();
			} catch (SQLException e) {
				
				System.err.println("Erro: " + e);
			}
		}
		fecharConexao(con);
	}

	public static void fecharConexao(Connection con, PreparedStatement preparador, ResultSet resultado) {
		if (resultado != null) {
			try {
				resultado.close();
			} catch (SQLException e) {
				System.err.println("Erro: " + e);
			}
		}
		fecharConexao(con, preparador);
	}
}
