package br.com.monthalcantara.service;

import java.util.ArrayList;
import java.util.List;

import br.com.monthalcantara.model.Cliente;

public class ClienteService {
	private static List<Cliente> lista = new ArrayList<Cliente>();

	public void cadastrar(Cliente cliente) {
		lista.add(cliente);
		
	}

	public static List<Cliente> getLista() {
		return lista;
	}

public void excluir(int index) {
	lista.remove(index);
}
public static void editarPorId(int index, Cliente cli) {
	lista.set(index,cli);
}

public static Cliente buscaPorId(int id) {
	return lista.get(id);
}
	
}
