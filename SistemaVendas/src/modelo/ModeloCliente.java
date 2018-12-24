package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloCliente {
	private int id;
	private String nome;
	private String telefone;
	private String endereco;
	private String info;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("id");
		listNomes.add("nome");
		listNomes.add("telefone");
		listNomes.add("endereco");
		listNomes.add("info");
		return listNomes ;
	}

}
