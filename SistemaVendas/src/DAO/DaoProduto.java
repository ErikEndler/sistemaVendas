package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import modelo.ModeloCategoria;
import modelo.ModeloCliente;
import modelo.ModeloProduto;

public class DaoProduto extends ConexaoMySql {
	/**
	 * insere produto no banco 
	 * @param modProduto
	 * @return
	 */
	public int salvarProdutoDAO(ModeloProduto modProduto) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_produto ( " 
					+ "produto_nome," 
					+ "produto_preco," 
					+ "produto_categoria,"
					+ "produto_descricao" 
					+ ") VALUES (" 
					+ "'" + modProduto.getNome() + "'," 
					+ "'" + modProduto.getPreco() + "'," 
					+ "'" + modProduto.getId_categoria() + "'," 
					+ "'" + modProduto.getDescricao() + "');");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	/**
	 * Excluir produto do banco
	 * @param pProduto_id
	 * @return boolean
	 */
	public boolean excluirProdutoDAO(int pProduto_id) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("DELETE FROM tbl_produto WHERE produto_id = '" + pProduto_id + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}

	/**
	 * Alterar dados do produto na tabela banco dados
	 * @param pModeloProduto
	 * @return boolean
	 */
	public boolean alterarProdutoDAO(ModeloProduto pModeloProduto) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("UPDATE tbl_produto SET" 
					+ "produto_nome = '" + pModeloProduto.getNome()+ "'," 
					+ "produto_preco = '" + pModeloProduto.getPreco() + "'," 
					+ "produto_categoria = '"+ pModeloProduto.getCategoria() + "'," 
					+ "produto_descricao = '" + pModeloProduto.getDescricao()+ "'" 
					+ "WHERE produto_id = '" + pModeloProduto.getId() + "';");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	/**
	 * retornar um produto do BD pelo codigo
	 * @param pIdProduto
	 * @return modeloProduto
	 */
	public ModeloProduto retornaProdutoDAO(int pIdProduto) {
		ModeloProduto modProduto = new ModeloProduto();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "p.produto_id,"
					+ "p.produto_nome,"
					+ "p.produto_preco,"
					+ "p.produto_categoria,"
					+ "p.produto_descricao,"
					+ "c.categoria_nome "
					+ "FROM tbl_produto p, tbl_categoria_produto c WHERE p.produto_id = '"+pIdProduto+"' and c.categoria_id = p.produto_categoria ;");
			
			while(this.getResultSet().next()) {
				modProduto.setId(this.getResultSet().getInt(1));
				modProduto.setNome(this.getResultSet().getString(2));
				modProduto.setPreco(this.getResultSet().getDouble(3));
				modProduto.setId_categoria(this.getResultSet().getInt(4));
				//modProduto.setCategoria(this.getResultSet().getString(4));
				modProduto.setDescricao(this.getResultSet().getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return modProduto;
	}
	/**
	 * retorna uma lista de produtos
	 * @return listamodeloProduto
	 */
	public ArrayList<ModeloProduto> retornarListaProdutosDAO(){
		ArrayList<ModeloProduto> listaModeloProdutos = new ArrayList<>();
		ModeloProduto modProduto = new ModeloProduto();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "p.produto_id,"
					+ "p.produto_nome,"
					+ "p.produto_preco,"
					+ "p.produto_categoria,"
					+ "p.produto_descricao,"
					+ "c.categoria_nome "
					+ "FROM tbl_produto p, tbl_categoria_produto c WHERE c.categoria_id=p.produto_categoria;");
			while(this.getResultSet().next()) {
				modProduto = new ModeloProduto();
				modProduto.setId(this.getResultSet().getInt(1));
				modProduto.setNome(this.getResultSet().getString(2));
				modProduto.setPreco(this.getResultSet().getDouble(3));
				modProduto.setId_categoria(this.getResultSet().getInt(4));
				modProduto.setDescricao(this.getResultSet().getString(5));
				modProduto.setCategoria(this.getResultSet().getString(6));
				listaModeloProdutos.add(modProduto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloProdutos;	
	}
	public ArrayList<ModeloProduto> retornarListaBuscaProdutoDAO(String pBox,String pBusca){
		ArrayList<ModeloProduto> listaModeloProduto = new ArrayList<>();
		ModeloProduto modProduto = new ModeloProduto();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "p.produto_id,"
					+ "p.produto_nome,"
					+ "p.produto_preco,"
					+ "p.produto_categoria,"
					+ "p.produto_descricao, "
					+ "c.categoria_nome "
					+ " FROM tbl_produto p, tbl_categoria_produto c where p.produto_" + pBusca + "= '" +pBox+"' "
					+ " and c.categoria_id = p.produto_categoria");
			while(this.getResultSet().next()) {
				modProduto = new ModeloProduto();
				modProduto.setId(this.getResultSet().getInt(1));
				modProduto.setNome(this.getResultSet().getString(2));
				modProduto.setPreco(this.getResultSet().getDouble(3));
				modProduto.setId_categoria(this.getResultSet().getInt(4));
				modProduto.setDescricao(this.getResultSet().getString(5));
				//this.executarSQL("SELECT categoria_nome FROM tbl_categoria_produto order by categoria_id");
				modProduto.setCategoria(this.getResultSet().getString(6));
				listaModeloProduto.add(modProduto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloProduto;	
	}
	public ArrayList<ModeloCategoria> retornaListaCategoriaProdutos(){
		ArrayList<ModeloCategoria> listaModeloCategoria = new ArrayList<>();
		try {
			this.conectar();
			this.executarSQL("SELECT categoria_id, categoria_nome from tbl_categoria_produto order by categoria_id");
			while(this.getResultSet().next()) {
				ModeloCategoria modeloCategoria = new ModeloCategoria();
				modeloCategoria.setId(this.getResultSet().getInt(1));
				modeloCategoria.setNome(this.getResultSet().getString(2));
				listaModeloCategoria.add(modeloCategoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaModeloCategoria;		
	}
}
