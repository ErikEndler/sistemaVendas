package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloUsuario {
	private int id;
	private String nome;
	private String login;
	private String senha;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("id");
		listNomes.add("nome");
		listNomes.add("login");
		listNomes.add("senha");
		return listNomes ;
	}

}
