package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import modelo.ModeloCliente;
import modelo.ModeloVendas;

public class DaoVendas extends ConexaoMySql {
	
	public int salvarVendaDAO(ModeloVendas pModVenda) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_vendas(" 
					+ "venda_data," 
					+ "venda_valorBruto," 
					+ "venda_valorDesconto,"
					+ "venda_fk_cliente,"
					+ "venda_fk_produto,"
					+ "venda_tipoPagamento,"
					+ "venda_obs "
					+ ") VALUES (" 
					+ "'" + pModVenda.getData() + "'," 
					+ "'" + pModVenda.getValor() + "'," 
					+ "'" + pModVenda.getDesconto() + "'," 
					+ "'" + pModVenda.getVenda_fk_cliente() + "',"
					+ "'" + pModVenda.getVenda_fk_produto() + "',"
					+ "'" + pModVenda.getTipoPagamento()+ "',"
					+ "'" +pModVenda.getObs()+ "');");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean excluirVendaDAO(int pVenda_id) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("DELETE FROM tbl_vendas WHERE venda_id = '" + pVenda_id + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public boolean alterarVendaDAO(ModeloVendas pModeloVendas) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("UPDATE tbl_vendas SET" 
					+ "venda_data = '" + pModeloVendas.getData()+ "'," 
					+ "venda_valorBruto = '" + pModeloVendas.getValor() + "'," 
					+ "venda_valorDesconto = '"+ pModeloVendas.getDesconto() + "'," 
					+ "venda_fk_cliente = '" + pModeloVendas.getVenda_fk_cliente()+ "',"
					+ "venda_fk_produto = '" + pModeloVendas.getVenda_fk_produto()+ "',"
					+ "venda_tipoPagamento = '" + pModeloVendas.getTipoPagamento()+ "',"
					+ "venda_obs = '" + pModeloVendas.getObs()+"'"
					+ "WHERE venda_id = '" + pModeloVendas.getId() + "';");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	public ModeloVendas retornaVendaDAO(int pIdVenda) {
		ModeloVendas modVenda = new ModeloVendas();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "venda_id,"
					+ "venda_data,"
					+ "venda_valorBruto,"
					+ "venda_valorDesconto,"
					+ "venda_fk_cliente,"
					+ "venda_fk_produto,"
					+ "venda_tipoPagamento,"
					+ "venda_obs "
					+ "FROM tbl_vendas WHERE venda_id = '"+pIdVenda+"';");
			
			while(this.getResultSet().next()) {
				modVenda.setId(this.getResultSet().getInt(1));
				modVenda.setData(this.getResultSet().getString(2));
				modVenda.setValor(this.getResultSet().getDouble(3));
				modVenda.setDesconto(this.getResultSet().getDouble(4));
				modVenda.setVenda_fk_cliente(this.getResultSet().getInt(5));
				modVenda.setVenda_fk_produto(this.getResultSet().getInt(6));
				modVenda.setTipoPagamento(this.getResultSet().getString(7));
				modVenda.setObs(this.getResultSet().getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return modVenda;
	}
	public ArrayList<ModeloVendas> retornarListaVendaDAO(){
		ArrayList<ModeloVendas> listaModeloVenda = new ArrayList<>();
		listaModeloVenda = null;
		ModeloVendas modVenda = new ModeloVendas();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "venda_id,"
					+ "venda_data,"
					+ "venda_valorBruto,"
					+ "venda_valorDesconto,"
					+ "venda_fk_cliente,"
					+ "venda_fk_produto,"
					+ "venda_tipoPagamento,"
					+ "venda_obs "
					+ "FROM tbl_vendas;");
			while(this.getResultSet().next()) {
				modVenda = new ModeloVendas();
				modVenda.setId(this.getResultSet().getInt(1));
				modVenda.setData(this.getResultSet().getString(2));
				modVenda.setValor(this.getResultSet().getDouble(3));
				modVenda.setDesconto(this.getResultSet().getDouble(4));
				modVenda.setVenda_fk_cliente(this.getResultSet().getInt(5));
				modVenda.setVenda_fk_produto(this.getResultSet().getInt(6));
				modVenda.setTipoPagamento(this.getResultSet().getString(7));
				modVenda.setObs(this.getResultSet().getString(8));
				listaModeloVenda.add(modVenda);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModeloVenda;	
	}

}
