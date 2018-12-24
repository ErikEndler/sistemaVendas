package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloProduto {
	private int id;
	private int id_categoria;
	private double preco;
	private String nome,descricao,categoria;
	private ArrayList list;

	public int getId() {
		return id;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("id");
		listNomes.add("nome");
		listNomes.add("preco");
		listNomes.add("id_categoria");
		listNomes.add("descricao");
		return listNomes ;
	}
}
