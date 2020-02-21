package br.com.monthalcantara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.monthalcantara.conexao.Conexao;
import br.com.monthalcantara.model.Cliente;

public class ClienteDAO {
	Connection con = Conexao.getConnection();
	Cliente cliente;
	PreparedStatement preparador;
	ResultSet resultado;
	List<Cliente> listaCli = new ArrayList<Cliente>();

	public List<Cliente> buscarTodos() {
		String sql = "select * from cjweb1";

		try {
			preparador = con.prepareStatement(sql);
			resultado = preparador.executeQuery();
			if (resultado.next()) {
				cliente = new Cliente();
				cliente.setEmail(resultado.getString("email"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setLogin(resultado.getString("login"));
				cliente.setSenha(resultado.getString("senha"));
				listaCli.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCli;
	}

	public void inserirCliente(Cliente cliente) {
		String sql = "insert into cjweb1 (nome, login, email, senha) values (?,?,?,?)";
		try {
			preparador = con.prepareStatement(sql);
			preparador.setString(1, cliente.getNome());
			preparador.setString(2, cliente.getLogin());
			preparador.setString(3, cliente.getEmail());
			preparador.setString(4, cliente.getSenha());
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conexao.fecharConexao(con, preparador);
	}
	public void excluirCliente(Cliente cliente) {
String sql = "Delete * from cjweb1 where id = " +cliente.getId();		
try {
	preparador = con.prepareStatement(sql);
	preparador.execute();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}
	
}