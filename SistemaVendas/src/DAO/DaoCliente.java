package DAO;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexoes.ConexaoMySql;
import modelo.ModeloCliente;
import modelo.ModeloProduto;

public class DaoCliente extends ConexaoMySql{
	
	public int salvarClienteDAO(ModeloCliente pModCliente) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_cliente(" 
					+ "cliente_nome," 
					+ "cliente_telefone," 
					+ "cliente_endereco,"
					+ "cliente_info" 
					+ ") VALUES (" 
					+ "'" + pModCliente.getNome() + "'," 
					+ "'" + pModCliente.getTelefone() + "'," 
					+ "'" + pModCliente.getEndereco() + "'," 
					+ "'" + pModCliente.getInfo() + "');");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean excluirClienteDAO(int pCliente_id) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("DELETE FROM tbl_cliente WHERE cliente_id = '" + pCliente_id + "';");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean alterarClienteDAO(ModeloCliente pModeloCliente) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("UPDATE tbl_cliente SET " 
					+ "cliente_nome = '" + pModeloCliente.getNome()+ "'," 
					+ "cliente_telefone = '" + pModeloCliente.getTelefone() + "'," 
					+ "cliente_endereco = '"+ pModeloCliente.getEndereco() + "'," 
					+ "cliente_info = '" + pModeloCliente.getInfo()+ "'" 
					+ " WHERE cliente_id = '" + pModeloCliente.getId() + "'");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public ModeloCliente retornaClienteDAO(int pIdCliente) {
		ModeloCliente modCliente = new ModeloCliente();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "cliente_id,"
					+ "cliente_nome,"
					+ "cliente_telefone,"
					+ "cliente_endereco,"
					+ "cliente_info "
					+ "FROM tbl_cliente WHERE cliente_id = '"+pIdCliente+"';");
			
			while(this.getResultSet().next()) {
				modCliente.setId(this.getResultSet().getInt(1));
				modCliente.setNome(this.getResultSet().getString(2));
				modCliente.setTelefone(this.getResultSet().getString(3));
				modCliente.setEndereco(this.getResultSet().getString(4));
				modCliente.setInfo(this.getResultSet().getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return modCliente;
	}
	public ArrayList<ModeloCliente> retornarListaClienteDAO(){
		ArrayList<ModeloCliente> listaModeloCliente = new ArrayList<>();
		//listaModeloCliente = null;
		ModeloCliente modCliente = new ModeloCliente();
		try {
			this.conectar();
			this.executarSQL("SELECT * FROM `tbl_cliente` WHERE 1 ");
					/*+ "cliente_id,"
					+ "cliente_nome,"
					+ "cliente_telefone,"
					+ "cliente_endereco,"
					+ "cliente_info "
					+ "FROM tbl_cliente;");*/
			while(this.getResultSet().next()) {
				modCliente = new ModeloCliente();
				modCliente.setId(this.getResultSet().getInt(1));
				modCliente.setNome(this.getResultSet().getString(2));
				modCliente.setTelefone(this.getResultSet().getString(3));
				modCliente.setEndereco(this.getResultSet().getString(4));
				modCliente.setInfo(this.getResultSet().getString(5));
				listaModeloCliente.add(modCliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloCliente;	
	}
	public ArrayList<ModeloCliente> retornarListaBuscaClienteDAO(String pBox,String pBusca){
		ArrayList<ModeloCliente> listaModeloCliente = new ArrayList<>();
		//listaModeloCliente = null;
		ModeloCliente modCliente = new ModeloCliente();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "cliente_id,"
					+ "cliente_nome,"
					+ "cliente_telefone,"
					+ "cliente_endereco,"
					+ "cliente_info "
					+ "FROM tbl_cliente where cliente_" + pBusca + "= '" +pBox+"';");
			while(this.getResultSet().next()) {
				modCliente = new ModeloCliente();
				modCliente.setId(this.getResultSet().getInt(1));
				modCliente.setNome(this.getResultSet().getString(2));
				modCliente.setTelefone(this.getResultSet().getString(3));
				modCliente.setEndereco(this.getResultSet().getString(4));
				modCliente.setInfo(this.getResultSet().getString(5));
				listaModeloCliente.add(modCliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloCliente;	
	}
	
}
