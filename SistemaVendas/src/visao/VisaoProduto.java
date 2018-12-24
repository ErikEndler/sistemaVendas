package visao;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controle.ControleCategoria;
import controle.ControleProduto;
import modelo.ModeloCategoria;
import modelo.ModeloProduto;
import util.JDoubleField;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class VisaoProduto extends ModeloCrud1 {
	
	private JLabel lblNome;
	private JTextField textField_id;
	private JTextField textField_nome;
	
	ArrayList<ModeloProduto> listaModeloProduto = new ArrayList<>();
	ControleProduto controleProduto = new ControleProduto();
	ModeloProduto modeloProduto = new ModeloProduto();
	
	private JLabel lblId;
	private JLabel lblPreo;
	private JLabel lblCategoria;
	private JLabel lblDescricao;
	private JTextArea textArea_descricao;
	private JComboBox comboBoxCategoria;
	private JDoubleField campoModeda;

	public VisaoProduto() {
		super();
		getBtnExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja Editar Item selecionado??");
				if(resposta==1 || resposta==2) {
					
				}else {
					int linha = getTabelaDados().getSelectedRow();
					int codigo= (int) getTabelaDados().getValueAt(linha, 0);
					Boolean confirmacao = controleProduto.excluirProdutoControle(codigo);
					if(!confirmacao) {
						JOptionPane.showMessageDialog(null,"Eroo ao Excluir Produto!");
					}else {
						JOptionPane.showMessageDialog(null,"Produto Excluido!");
					}
				}
			}
		});
		getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTipoTabela(1);
				preencherTabela();
			}
		});
		comboBoxBusca();
		
		getTabelaDados().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getBtnEditar().setEnabled(true);
				getBtnExcluir().setEnabled(true);
			}
		});
		getBtnEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja Editar Item selecionado??");
				if(resposta==1 || resposta==2) {
					
				}else {
					setTipoSalvar(1);
					int linha = getTabelaDados().getSelectedRow();
					int codigo= (int) getTabelaDados().getValueAt(linha, 0);
					try {
						modeloProduto = controleProduto.retornarProdutoControle(codigo);
						campoModeda.setValue(modeloProduto.getPreco());
						textArea_descricao.setText(modeloProduto.getDescricao());
						textField_nome.setText(modeloProduto.getNome());
						textField_id.setText(String.valueOf(modeloProduto.getId()));
						JOptionPane.showMessageDialog(null,modeloProduto.getId_categoria());
						JOptionPane.showMessageDialog(null,modeloProduto.getCategoria());
						preencherCombobox();
						comboBoxCategoria.setSelectedIndex((modeloProduto.getId_categoria()-1));
						
						textField_id.setEnabled(false);
						textField_nome.setEnabled(true);
						campoModeda.setEnabled(true);
						textArea_descricao.setEnabled(true);
						comboBoxCategoria.setEnabled(true);
						getBtnSalvar().setEnabled(true);
						getBtnEditar().setEnabled(false);
						getTabbedPane().setSelectedIndex(1);
					} catch (Exception ex) {
						// TODO: handle exception
					}
				}
			}
		});
		getBtnSalvar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTipoSalvar()==0) {
					acaoSalvar();
				}
				if(getTipoSalvar()==1) {
					acaoEditar();
				}
				textField_id.setText("");
				textField_nome.setText("");
				campoModeda.setText("");
				textArea_descricao.setText("");
				comboBoxCategoria.removeAll();
				getBtnSalvar().setEnabled(false);
				getTabbedPane().setSelectedIndex(0);
				preencherTabela();
				comboBoxCategoria.removeAllItems();
			}
		});
		getBtnLocalizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTabbedPane().setSelectedIndex(0);
				setTipoTabela(0);
				preencherTabela();
			}
		});
		getBtnNovo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTipoSalvar(0);
				getTabbedPane().setSelectedIndex(1);
				acaoNovo();
			}
		});
		getBtnEditar().setEnabled(false);
		getBtnSalvar().setEnabled(false);
		getBtnExcluir().setEnabled(false);
		setTitle("Produtos");
		getPainelDados().setLayout(null);	
			
		lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 44, 46, 14);
		getPainelDados().add(lblNome);
		
		lblId = new JLabel("ID");
		lblId.setBounds(12, 15, 56, 16);
		getPainelDados().add(lblId);
		
		lblPreo = new JLabel("Preço R$:");
		lblPreo.setBounds(12, 71, 56, 16);
		getPainelDados().add(lblPreo);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 100, 56, 16);
		getPainelDados().add(lblCategoria);
		
		lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(12, 129, 56, 16);
		getPainelDados().add(lblDescricao);
		
		textField_id = new JTextField();
		textField_id.setEnabled(false);
		textField_id.setBounds(80, 12, 116, 22);
		getPainelDados().add(textField_id);
		textField_id.setColumns(10);
		
		textField_nome = new JTextField();
		textField_nome.setEnabled(false);
		//textField_nome.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textField_nome.setBounds(80, 40, 503, 22);
		getPainelDados().add(textField_nome);
		textField_nome.setColumns(10);
		
		textArea_descricao = new JTextArea();
		textArea_descricao.setEnabled(false);
		textArea_descricao.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_descricao.setBounds(80, 132, 503, 103);
		getPainelDados().add(textArea_descricao);
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setEnabled(false);
		comboBoxCategoria.setBounds(80, 97, 167, 22);
		getPainelDados().add(comboBoxCategoria);
		
		campoModeda = new JDoubleField();
		campoModeda.setEnabled(false);
		campoModeda.setBounds(80, 68, 103, 22);
		getPainelDados().add(campoModeda);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(mascaraMoeda);
		formattedTextField.setBounds(208, 68, 103, 22);
		formattedTextField.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getPainelDados().add(formattedTextField);
		
		/*JFormattedTextField formattedTextField = new JFormattedTextField(mascaraMoeda);
		formattedTextField.setBounds(224, 68, 124, 22);
		//formattedTextField.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		//formattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT); 
		//formattedTextField.setFormatterFactory( new javax.swing.text.DefaultFormatterFactory(	new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("R$ #,###.00"))));
		getPainelDados().add(formattedTextField);*/
		
		this.getLblTituloJanela().setText("Produtos");
		
		getTabelaDados().setLayout(null);
		carregarProdutos();
	}
	void comboBoxBusca() {
		for(int i=0;i<modeloProduto.getCamposNome().size();i++) {
			getComboBoxCampos().addItem(modeloProduto.getCamposNome().get(i));
		}
	}
	/**
	 * preenche a tabela de produtos com os produtos cadastrados no banco
	 */
	private void carregarProdutos() {
		listaModeloProduto = controleProduto.retornaListaProdutoControle();
		DefaultTableModel modelo = (DefaultTableModel) getTabelaDados().getModel();
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisaoProduto frame = new VisaoProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	ArrayList linhaTabela() {
		listaModeloProduto = controleProduto.retornaListaProdutoControle();
		if(listaModeloProduto==null) {
			JOptionPane.showMessageDialog(null,"Não ha itens para exibir");
		}else {
			ArrayList dados = new ArrayList();
			for(int i =0; i<listaModeloProduto.size();i++) {
				dados.add(new Object[] {
						listaModeloProduto.get(i).getId(),
						listaModeloProduto.get(i).getNome(),
						listaModeloProduto.get(i).getPreco(),
						listaModeloProduto.get(i).getId_categoria(),
						listaModeloProduto.get(i).getDescricao()
				});
			}
			return dados;
		}
		return listaModeloProduto;
		
	}
	@Override
	ArrayList linhaTabelaPesquisa() {
		String string = getComboBoxCampos().getSelectedItem().toString();
		listaModeloProduto= controleProduto.retornaListaBuscaProdutoControle(getTextFieldCampoBusca().getText(), string );
		ArrayList dados = new ArrayList();
		for(int i =0; i<listaModeloProduto.size();i++) {
			dados.add(new Object[] {
					listaModeloProduto.get(i).getId(),
					listaModeloProduto.get(i).getNome(),
					listaModeloProduto.get(i).getPreco(),
					listaModeloProduto.get(i).getId_categoria(),
					listaModeloProduto.get(i).getDescricao()
			});
		}
		return dados;
	}
	@Override
	String[] colunaTAbela() {
		int cont = modeloProduto.getCamposNome().size();
		String[] Colunas = new String[cont];
		for(int i=0;i<cont;i++) {
			Colunas[i]=modeloProduto.getCamposNome().get(i);
		}
		return Colunas;		
	}
	@Override
	void acaoNovo() {
		getBtnSalvar().setEnabled(true);
		textField_nome.setEnabled(true);
		campoModeda.setEnabled(true);
		textArea_descricao.setEnabled(true);
		comboBoxCategoria.setEnabled(true);
		preencherCombobox();
	}
	@Override
	void acaoSalvar() {
		modeloProduto.setNome(textField_nome.getText());
		modeloProduto.setPreco(campoModeda.getValue());
		modeloProduto.setDescricao(textArea_descricao.getText());
		modeloProduto.setCategoria(comboBoxCategoria.getSelectedItem().toString());
		modeloProduto.setId_categoria((int) modeloProduto.getList().get(comboBoxCategoria.getSelectedIndex()));
		JOptionPane.showMessageDialog(null,modeloProduto.getList().get(comboBoxCategoria.getSelectedIndex()));
		if(controleProduto.salvarProdutoControle(modeloProduto)>0) {
			JOptionPane.showMessageDialog(this,"Produto cadastrado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(this,"Eroo ao cadastrar produto!");
		}
	}
	@Override
	void acaoEditar() {
		modeloProduto.setId(Integer.parseInt(textField_id.getText()));
		modeloProduto.setNome(textField_nome.getText());
		modeloProduto.setPreco(campoModeda.getValue());
		modeloProduto.setDescricao(textArea_descricao.getText());
		modeloProduto.setCategoria(comboBoxCategoria.getSelectedItem().toString());
		JOptionPane.showMessageDialog(null,comboBoxCategoria.getSelectedItem().toString());
		modeloProduto.setId_categoria(comboBoxCategoria.getSelectedIndex());
		if(controleProduto.alterarProdutoControle(modeloProduto)==true) {
			JOptionPane.showMessageDialog(this,"Produto Alterado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(this,"Eroo ao Alterar produto!");
		}
		
	}
	void preencherCombobox() {
		comboBoxCategoria.removeAllItems();
		ArrayList lista = new ArrayList<>();
		ControleCategoria conCategoria = new ControleCategoria();
		ArrayList<ModeloCategoria> listaModeloCategoria = conCategoria.retornaListaCategoriaControle();
		int cont = controleProduto.retornaListaCategoriaProdutos().size();
		for(int i =0; i<cont;i++) {
			comboBoxCategoria.addItem(listaModeloCategoria.get(i).getNome());
			lista.add(listaModeloCategoria.get(i).getId());
		}
		modeloProduto.setList(lista);
	}
}
