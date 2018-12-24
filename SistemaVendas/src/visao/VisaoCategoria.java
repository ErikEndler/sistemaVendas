package visao;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controle.ControleCategoria;
import modelo.ModeloCategoria;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisaoCategoria extends ModeloCrud1 {
	private JTextField textField_id;
	private JTextField textField_nome;
	private Component lblId;
	private Component lblNome;
	private JLabel lblDescrio;
	private JTextArea textArea_descricao;
	//private int tipoSalvar;
	
	ArrayList<ModeloCategoria> listaModeloCategoria = new ArrayList<>();
	private ModeloCategoria modeloCategoria = new ModeloCategoria();
	private ControleCategoria controleCategoria = new ControleCategoria();
	
	VisaoCategoria(){
		super();
		getTabelaDados().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getBtnExcluir().setEnabled(true);
				getBtnEditar().setEnabled(true);
			}
		});
		comboBoxBusca();
		preencherTabela();
		getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTipoTabela(1);
				preencherTabela();
			}
		});
		getBtnLocalizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTabbedPane().setSelectedIndex(0);
				preencherTabela();
			}
		});
		getBtnExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja Excluir Item selecionado??");
				if(resposta==1 || resposta==2) {
					
				}else {
					int linha = getTabelaDados().getSelectedRow();
					int codigo= (int) getTabelaDados().getValueAt(linha, 0);
					Boolean confirmacao = controleCategoria.excluirCategoriaControle(codigo);
					if(!confirmacao) {
						JOptionPane.showMessageDialog(null,"Eroo ao Excluir Categoria!");
					}else {
						JOptionPane.showMessageDialog(null,"Categoria Excluida!");
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
				textArea_descricao.setText("");
				getBtnSalvar().setEnabled(false);
				getTabbedPane().setSelectedIndex(0);
				preencherTabela();
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
						modeloCategoria = controleCategoria.retornarCategoriaControle(codigo);
						textArea_descricao.setText(modeloCategoria.getDescricao());
						textField_nome.setText(modeloCategoria.getNome());
						textField_id.setText(String.valueOf(modeloCategoria.getId()));
						
						textField_id.setEnabled(false);
						textField_nome.setEnabled(true);
						textArea_descricao.setEnabled(true);
						getBtnSalvar().setEnabled(true);
						getBtnEditar().setEnabled(false);
						getTabbedPane().setSelectedIndex(1);
					} catch (Exception ex) {
						// TODO: handle exception
					}
				}
			}
		});
		getBtnNovo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTipoSalvar(0);
				getTabbedPane().setSelectedIndex(1);
				acaoNovo();
			}
		});
		setTitle("Categoria Produtos");
		getPainelDados().setLayout(null);
		getLblTituloJanela().setText("Categoria Produtos");
		
		lblId = new JLabel("ID");
		lblId.setBounds(12, 13, 56, 16);
		getPainelDados().add(lblId);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 42, 56, 16);
		getPainelDados().add(lblNome);
		
		lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(12, 71, 56, 16);
		getPainelDados().add(lblDescrio);
		
		textField_id = new JTextField();
		textField_id.setEnabled(false);
		textField_id.setBounds(80, 10, 116, 22);
		getPainelDados().add(textField_id);
		textField_id.setColumns(10);
		
		textField_nome = new JTextField();
		textField_nome.setEnabled(false);
		textField_nome.setBounds(80, 39, 488, 22);
		getPainelDados().add(textField_nome);
		textField_nome.setColumns(10);
		
		textArea_descricao = new JTextArea();
		textArea_descricao.setEnabled(false);
		textArea_descricao.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea_descricao.setBounds(80, 74, 488, 174);
		getPainelDados().add(textArea_descricao);
		
		getBtnExcluir().setEnabled(false);
		getBtnSalvar().setEnabled(false);
		getBtnEditar().setEnabled(false);
		
	}
	void acaoSalvar() {
		modeloCategoria.setNome(textField_nome.getText());
		modeloCategoria.setDescricao(textArea_descricao.getText());
		if(controleCategoria.salvarCategoriaControle(modeloCategoria)>0) {
			JOptionPane.showMessageDialog(this,"Produto cadastrado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(this,"Eroo ao cadastrar produto!");
		}
	}
	void acaoEditar() {
		modeloCategoria.setNome(textField_nome.getText());
		modeloCategoria.setDescricao(textArea_descricao.getText());
		if(controleCategoria.alterarCategoriaControle(modeloCategoria)==true) {
			JOptionPane.showMessageDialog(this,"Produto Alterado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(this,"Eroo ao Alterar produto!");
		}
	}
	void acaoNovo() {
		getBtnSalvar().setEnabled(true);
		textField_nome.setEnabled(true);
		textArea_descricao.setEnabled(true);
	}
	@Override
	public void preencherTabela() {
		// TODO Auto-generated method stub
		super.preencherTabela();
	}
	String[]  colunaTAbela() {
		int cont = modeloCategoria.getCamposNome().size();
		String[] Colunas = new String[cont];
		for(int i=0;i<cont;i++) {
			Colunas[i]=modeloCategoria.getCamposNome().get(i);
		}
		return Colunas;	
	}
	
	ArrayList linhaTabela(){
		listaModeloCategoria = controleCategoria.retornaListaCategoriaControle();
		if(listaModeloCategoria==null) {
			JOptionPane.showMessageDialog(null,"Não ha itens para exibir");
		}else {
			ArrayList dados = new ArrayList();
			for(int i =0; i<listaModeloCategoria.size();i++) {
				dados.add(new Object[] {
						listaModeloCategoria.get(i).getId(),
						listaModeloCategoria.get(i).getNome(),
						listaModeloCategoria.get(i).getDescricao()
				});
			}
			return dados;
		}
		return listaModeloCategoria;
		
	}
	ArrayList linhaTabelaPesquisa(){
		String string = getComboBoxCampos().getSelectedItem().toString();
		listaModeloCategoria= controleCategoria.retornaListaBuscaClienteControle(getTextFieldCampoBusca().getText(), string );
		ArrayList dados = new ArrayList();
		for(int i =0; i<listaModeloCategoria.size();i++) {
			dados.add(new Object[] {
					listaModeloCategoria.get(i).getId(),
					listaModeloCategoria.get(i).getNome(),
					listaModeloCategoria.get(i).getDescricao()
			});
		}
		return dados;
	}
	void comboBoxBusca() {
		for(int i=0;i<modeloCategoria.getCamposNome().size();i++) {
			getComboBoxCampos().addItem(modeloCategoria.getCamposNome().get(i));
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisaoCategoria frame = new VisaoCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
