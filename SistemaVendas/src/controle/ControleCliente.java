package controle;

import java.util.ArrayList;

import DAO.DaoCliente;
import modelo.ModeloCliente;

public class ControleCliente {
	
	private DaoCliente daoCliente = new DaoCliente();
	
	public int salvarClienteControle(ModeloCliente pModeloCliente) {
		return this.daoCliente.salvarClienteDAO(pModeloCliente);
	}
	public boolean excluirClienteControle(int pCodigo) {
		return this.daoCliente.excluirClienteDAO(pCodigo);
	}
	public boolean alterarClienteControle(ModeloCliente pModeloCliente) {
		return this.daoCliente.alterarClienteDAO(pModeloCliente);
	}
	public ModeloCliente retornarClienteControle(int pCodigo) {
		return this.daoCliente.retornaClienteDAO(pCodigo);
	}
	public ArrayList<ModeloCliente> retornaListaClienteControle(){
		return this.daoCliente.retornarListaClienteDAO();
	}
	public ArrayList<ModeloCliente> retornaListaBuscaClienteControle(String pBox,String pBusca){
		return this.daoCliente.retornarListaBuscaClienteDAO(pBox, pBusca);
	}
}
