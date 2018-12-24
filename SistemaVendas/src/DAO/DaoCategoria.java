package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import modelo.ModeloCategoria;
import modelo.ModeloCliente;

public class DaoCategoria extends ConexaoMySql {
	
	public int salvarCategoriaDAO(ModeloCategoria pModCategoria) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_categoria_produto(" 
					+ "categoria_nome," 
					+ "categoria_descricao" 
					+ ") VALUES (" 
					+ "'" + pModCategoria.getNome() + "',"  
					+ "'" + pModCategoria.getDescricao() + "');");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean excluirCategoriaDAO(int pCategoria_id) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("DELETE FROM tbl_categoria_produto WHERE tbl_categoria_produto.categoria_id = '" + pCategoria_id + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean alterarCategoriaDAO(ModeloCategoria pModeloCategoria) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("UPDATE tbl_categoria_produto SET " 
					+ "categoria_nome = '" + pModeloCategoria.getNome()+ "'," 
					+ "categoria_descricao = '" + pModeloCategoria.getDescricao()+ "' " 
					+ "WHERE categoria_id = '" + pModeloCategoria.getId() + "'");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public ModeloCategoria retornaCategoriaDAO(int pIdCategoria) {
		ModeloCategoria modCategoria = new ModeloCategoria();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "categoria_id,"
					+ "categoria_nome,"
					+ "categoria_descricao"
					+ " FROM tbl_categoria_produto WHERE categoria_id = '"+pIdCategoria+"';");
			
			while(this.getResultSet().next()) {
				modCategoria.setId(this.getResultSet().getInt(1));
				modCategoria.setNome(this.getResultSet().getString(2));
				modCategoria.setDescricao(this.getResultSet().getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return modCategoria;
	}
	public ArrayList<ModeloCategoria> retornarListaCategoriaDAO(){
		ArrayList<ModeloCategoria> listaModeloCategoria = new ArrayList<>();
		ModeloCategoria modCategoria = new ModeloCategoria();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "categoria_id,"
					+ "categoria_nome,"
					+ "categoria_descricao "
					+ "FROM tbl_categoria_produto;");
			while(this.getResultSet().next()) {
				modCategoria = new ModeloCategoria();
				modCategoria.setId(this.getResultSet().getInt(1));
				modCategoria.setNome(this.getResultSet().getString(2));
				modCategoria.setDescricao(this.getResultSet().getString(3));
				listaModeloCategoria.add(modCategoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloCategoria;	
	}
	public ArrayList<ModeloCategoria> retornarListaBuscaCategoriaDAO(String pBox,String pBusca){
		ArrayList<ModeloCategoria> listaModeloCategoria = new ArrayList<>();
		ModeloCategoria modClategoria = new ModeloCategoria();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "categoria)id,"
					+ "categoria_nome,"
					+ "categoria_descricao "
					+ "FROM tbl_categoria_produto where categoria_" + pBusca + "= '" +pBox+"';");
			while(this.getResultSet().next()) {
				modClategoria = new ModeloCategoria();
				modClategoria.setId(this.getResultSet().getInt(1));
				modClategoria.setNome(this.getResultSet().getString(2));
				modClategoria.setDescricao(this.getResultSet().getString(3));
				listaModeloCategoria.add(modClategoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloCategoria;	
	}

}
