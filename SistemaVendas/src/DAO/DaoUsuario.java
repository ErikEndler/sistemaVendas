package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import modelo.ModeloCategoria;
import modelo.ModeloUsuario;

public class DaoUsuario extends ConexaoMySql{
	public int salvarUsuarioDAO(ModeloUsuario pModUsuario) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_usuario(" 
					+ "usuario_nome," 
					+ "usuario_login,"
					+ "usuario_senha"
					+ ") VALUES (" 
					+ "'" + pModUsuario.getNome() + "'," 
					+ "'" + pModUsuario.getLogin() + "'," 
					+ "'" + pModUsuario.getSenha() + "');");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean excluirUsuarioDAO(int pUsuario_id) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("DELETE FROM tbl_usuario WHERE usuario_id = '" + pUsuario_id + "';");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean alterarUsuarioDAO(ModeloUsuario pModeloUsuario) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("UPDATE tbl_usuario SET" 
					+ "usuario_nome = '" + pModeloUsuario.getNome()+ "'," 
					+ "usuario_login = '" + pModeloUsuario.getLogin()+ "'," 
					+ "usuario_senha = '" + pModeloUsuario.getSenha()+ "'"
					+ " WHERE usuario_id = '" + pModeloUsuario.getId() + "'");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public ModeloUsuario retornaUsuarioIdDAO(int pIdUsuario) {
		ModeloUsuario modUsuario = new ModeloUsuario();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "usuario_id,"
					+ "usuario_nome,"
					+ "usuario_login,"
					+ "usuario_senha"
					+ " FROM tbl_usuario WHERE usuario_id = '"+pIdUsuario+"';");
			
			while(this.getResultSet().next()) {
				modUsuario.setId(this.getResultSet().getInt(1));
				modUsuario.setNome(this.getResultSet().getString(2));
				modUsuario.setLogin(this.getResultSet().getString(3));
				modUsuario.setSenha(this.getResultSet().getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return modUsuario;
	}
	public ArrayList<ModeloUsuario> retornarListaUsuarioDAO(){
		ArrayList<ModeloUsuario> listaModeloUsuario = new ArrayList<>();
		listaModeloUsuario = null;
		ModeloUsuario modUsuario = new ModeloUsuario();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "usuario_id,"
					+ "usuario_nome,"
					+ "usuario_login,"
					+ "usuario_senha "
					+ "FROM tbl_usuario;");
			while(this.getResultSet().next()) {
				modUsuario = new ModeloUsuario();
				modUsuario.setId(this.getResultSet().getInt(1));
				modUsuario.setNome(this.getResultSet().getString(2));
				modUsuario.setLogin(this.getResultSet().getString(3));
				modUsuario.setSenha(this.getResultSet().getString(4));
				listaModeloUsuario.add(modUsuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloUsuario;	
	}
	

}
