package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloCategoria {
	private int id;
	private String nome;
	private String descricao;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("id");
		listNomes.add("nome");
		listNomes.add("descricao");
		return listNomes ;
	}

}
