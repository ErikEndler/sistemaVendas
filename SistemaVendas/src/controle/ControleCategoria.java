package controle;

import java.util.ArrayList;

import DAO.DaoCategoria;
import modelo.ModeloCategoria;
import modelo.ModeloCliente;

public class ControleCategoria {
	
private DaoCategoria daoCategoria = new DaoCategoria();
	
	public int salvarCategoriaControle(ModeloCategoria pModeloCategoria) {
		return this.daoCategoria.salvarCategoriaDAO(pModeloCategoria);
	}
	public boolean excluirCategoriaControle(int pCodigo) {
		return this.daoCategoria.excluirCategoriaDAO(pCodigo);
	}
	public boolean alterarCategoriaControle(ModeloCategoria pModeloCategoria) {
		return this.daoCategoria.alterarCategoriaDAO(pModeloCategoria);
	}
	public ModeloCategoria retornarCategoriaControle(int pCodigo) {
		return this.daoCategoria.retornaCategoriaDAO(pCodigo);
	}
	public ArrayList<ModeloCategoria> retornaListaCategoriaControle(){
		return this.daoCategoria.retornarListaCategoriaDAO();
	}
	public ArrayList<ModeloCategoria> retornaListaBuscaClienteControle(String pBox,String pBusca){
		return this.daoCategoria.retornarListaBuscaCategoriaDAO(pBox, pBusca);
	}

}
