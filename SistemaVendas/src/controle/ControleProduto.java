package controle;

import java.util.ArrayList;

import DAO.DaoProduto;
import modelo.ModeloCategoria;
import modelo.ModeloCliente;
import modelo.ModeloProduto;

public class ControleProduto {
	private DaoProduto daoProduto = new DaoProduto();
	/**
	 * salva produtos controle
	 * @param pModeloProduto
	 * @return int
	 */
	public int salvarProdutoControle(ModeloProduto pModeloProduto) {
		return this.daoProduto.salvarProdutoDAO(pModeloProduto);
	}
	/**
	 * exclui um produto pelo codigo
	 * @param pCodigo
	 * @return booblean
	 */
	public boolean excluirProdutoControle(int pCodigo) {
		return this.daoProduto.excluirProdutoDAO(pCodigo);
	}
	/**
	 * alterar um produto
	 * @param pModeloProduto
	 * @return boolean
	 */
	public boolean alterarProdutoControle(ModeloProduto pModeloProduto) {
		return this.daoProduto.alterarProdutoDAO(pModeloProduto);
	}
	/**
	 * retirna produto pelo codigo
	 * @param pCodigo
	 * @return modelo produto
	 */
	public ModeloProduto retornarProdutoControle(int pCodigo) {
		return this.daoProduto.retornaProdutoDAO(pCodigo);
	}
	/**
	 * retorna lista de produtos
	 * @return lista modelo produtos
	 */
	public ArrayList<ModeloProduto> retornaListaProdutoControle(){
		return this.daoProduto.retornarListaProdutosDAO();
	}
	public ArrayList<ModeloCategoria> retornaListaCategoriaProdutos(){
		return this.daoProduto.retornaListaCategoriaProdutos();
		
	}
	public ArrayList<ModeloProduto> retornaListaBuscaProdutoControle(String pBox,String pBusca){
		return this.daoProduto.retornarListaBuscaProdutoDAO(pBox, pBusca);
	}
}
