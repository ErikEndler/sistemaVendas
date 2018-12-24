package controle;

import java.util.ArrayList;

import DAO.DaoUsuario;
import modelo.ModeloUsuario;

public class ControleUsuario {
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	
	public int salvarUsuarioControle(ModeloUsuario pModeloUsuario) {
		return this.daoUsuario.salvarUsuarioDAO(pModeloUsuario);
	}
	public boolean excluirUsuarioControle(int pCodigo) {
		return this.daoUsuario.excluirUsuarioDAO(pCodigo);
	}
	public boolean alterarUsuarioControle(ModeloUsuario pModeloUsuario) {
		return this.daoUsuario.alterarUsuarioDAO(pModeloUsuario);
	}
	public ModeloUsuario retornarUsuarioeControle(int pCodigo) {
		return this.daoUsuario.retornaUsuarioIdDAO(pCodigo);
	}
	public ArrayList<ModeloUsuario> retornaListausuarioControle(){
		return this.daoUsuario.retornarListaUsuarioDAO();
	}

}
