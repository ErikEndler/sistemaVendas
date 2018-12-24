package visao;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import controle.ControleCliente;
import modelo.ModeloCliente;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisaoCliente extends ModeloCrud1 {
	private JTextField textField_id;
	private JTextField textField_nome;
	private JTextField textField_telefone;
	private JTextField textField_endereco;
	private JLabel lblId;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEndereco;
	private JLabel lblInformaes;
	private JTextArea textArea_info;
	//private int tipoSalvar;
	
	ArrayList<ModeloCliente> listaModeloCliente = new ArrayList<>();
	private ModeloCliente modeloCliente = new ModeloCliente();
	private ControleCliente controleCliente = new ControleCliente();
	
	public VisaoCliente() {
		super();
		getTabelaDados().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getBtnEditar().setEnabled(true);
				getBtnExcluir().setEnabled(true);
			}
		});
		preencherTabela();
		comboBoxBusca();
		getBtnExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja Excluir Item selecionado??");
				if(resposta==1 || resposta==2) {
					
				}else {
					int linha = getTabelaDados().getSelectedRow();
					int codigo= (int) getTabelaDados().getValueAt(linha, 0);
					Boolean confirmacao = controleCliente.excluirClienteControle(codigo);
					if(!confirmacao) {
						JOptionPane.showMessageDialog(null,"Eroo ao Excluir produto!");
					}else {
						JOptionPane.showMessageDialog(null,"Produto Excluido!");
					}
				}
			}
		});
		getBtnEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja Editar Item selecionado??");
				if(resposta==1 || resposta==2) {
					
				}else {
					setTipoSalvar(1);
					int linha = getTabelaDados().getSelectedRow();
					int codigo= (int) getTabelaDados().getValueAt(linha, 0);
					try {
						modeloCliente = controleCliente.retornarClienteControle(codigo);
						textField_id.setText(String.valueOf(modeloCliente.getId()));
						textField_id.setEnabled(false);
						textField_nome.setText(modeloCliente.getNome());
						textField_nome.setEnabled(true);
						textField_telefone.setText(modeloCliente.getTelefone());
						textField_telefone.setEnabled(true);
						textField_endereco.setText(modeloCliente.getEndereco());
						textField_endereco.setEnabled(true);
						textArea_info.setText(modeloCliente.getInfo());
						textArea_info.setEnabled(true);
						getBtnSalvar().setEnabled(true);
						getBtnEditar().setEnabled(false);
						getTabbedPane().setSelectedIndex(1);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		});
		getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTipoTabela(1);
				preencherTabela();
			}
		});
		
		getBtnEditar().setEnabled(false);
		getBtnSalvar().setEnabled(false);
		getBtnExcluir().setEnabled(false);
		this.getLblTituloJanela().setText("Clientes");
		
		getBtnLocalizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTabbedPane().setSelectedIndex(0);
				setTipoTabela(0);
				preencherTabela();
			}
		});
		getBtnNovo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTipoSalvar(0);
				getTabbedPane().setSelectedIndex(1);
				acaoNovo();
			}
		});
		getBtnSalvar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(getTipoSalvar()==0) {
					acaoSalvar();
				}
				if(getTipoSalvar()==1) {
					acaoEditar();
				}
				textField_endereco.setText("");
				textField_id.setText("");
				textField_nome.setText("");
				textField_telefone.setText("");
				textArea_info.setText("");
				getBtnSalvar().setEnabled(false);
				getTabbedPane().setSelectedIndex(0);
				preencherTabela();
			}
		});
		setTitle("Produtos");
		getPainelDados().setLayout(null);	
		
		lblId = new JLabel("ID");
		lblId.setBounds(12, 13, 56, 16);
		getPainelDados().add(lblId);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 42, 56, 16);
		getPainelDados().add(lblNome);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(12, 71, 56, 16);
		getPainelDados().add(lblTelefone);
		
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(12, 100, 56, 16);
		getPainelDados().add(lblEndereco);
		
		lblInformaes = new JLabel("Informações");
		lblInformaes.setBounds(12, 129, 71, 16);
		getPainelDados().add(lblInformaes);
		
		textField_id = new JTextField();
		textField_id.setEnabled(false);
		textField_id.setBounds(90, 10, 116, 22);
		getPainelDados().add(textField_id);
		textField_id.setColumns(10);
		
		textField_nome = new JTextField();
		textField_nome.setEnabled(false);
		textField_nome.setBounds(90, 39, 437, 22);
		getPainelDados().add(textField_nome);
		textField_nome.setColumns(10);
		
		textField_telefone = new JTextField();
		textField_telefone.setEnabled(false);
		textField_telefone.setBounds(90, 68, 204, 22);
		getPainelDados().add(textField_telefone);
		textField_telefone.setColumns(10);
		
		textField_endereco = new JTextField();
		textField_endereco.setEnabled(false);
		textField_endereco.setBounds(90, 97, 437, 22);
		getPainelDados().add(textField_endereco);
		textField_endereco.setColumns(10);
		
		textArea_info = new JTextArea();
		textArea_info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea_info.setEnabled(false);
		textArea_info.setBounds(100, 132, 427, 55);
		getPainelDados().add(textArea_info);
	}
	void comboBoxBusca() {
		for(int i=0;i<modeloCliente.getCamposNome().size();i++) {
			getComboBoxCampos().addItem(modeloCliente.getCamposNome().get(i));
		}
	}
	@Override
	public void preencherTabela() {
		// TODO Auto-generated method stub
		super.preencherTabela();
	}
	
	String[]  colunaTAbela() {
		int cont = modeloCliente.getCamposNome().size();
		String[] Colunas = new String[cont];
		for(int i=0;i<cont;i++) {
			Colunas[i]=modeloCliente.getCamposNome().get(i);
		}
		return Colunas;	
	}
	
	ArrayList linhaTabela(){
		listaModeloCliente = controleCliente.retornaListaClienteControle();
		ArrayList dados = new ArrayList();
		for(int i =0; i<listaModeloCliente.size();i++) {
			dados.add(new Object[] {
					listaModeloCliente.get(i).getId(),
					listaModeloCliente.get(i).getNome(),
					listaModeloCliente.get(i).getTelefone(),
					listaModeloCliente.get(i).getEndereco(),
					listaModeloCliente.get(i).getInfo()
			});
		}
		return dados;
	}
	
	ArrayList linhaTabelaPesquisa(){
		String string = getComboBoxCampos().getSelectedItem().toString();
		listaModeloCliente= controleCliente.retornaListaBuscaClienteControle(getTextFieldCampoBusca().getText(), string );
		ArrayList dados = new ArrayList();
		for(int i =0; i<listaModeloCliente.size();i++) {
			dados.add(new Object[] {
					listaModeloCliente.get(i).getId(),
					listaModeloCliente.get(i).getNome(),
					listaModeloCliente.get(i).getTelefone(),
					listaModeloCliente.get(i).getEndereco(),
					listaModeloCliente.get(i).getInfo()
			});
		}
		return dados;
	}
	
	void acaoSalvar(){
		modeloCliente.setNome(textField_nome.getText());
		modeloCliente.setTelefone(textField_telefone.getText());
		modeloCliente.setEndereco(textField_endereco.getText());
		modeloCliente.setInfo(textArea_info.getText());
		if(controleCliente.salvarClienteControle(modeloCliente)>0) {
			JOptionPane.showMessageDialog(this,"Produto cadastrado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(this,"Eroo ao cadastrar produto!");
		}
	}
	void acaoNovo() {
		getBtnSalvar().setEnabled(true);
		textField_nome.setEnabled(true);
		textField_endereco.setEnabled(true);
		textField_telefone.setEnabled(true);
		textArea_info.setEnabled(true);
	}
	void acaoEditar() {
		modeloCliente.setNome(textField_nome.getText());
		modeloCliente.setTelefone(textField_telefone.getText());
		modeloCliente.setEndereco(textField_endereco.getText());
		modeloCliente.setInfo(textArea_info.getText());
		if(controleCliente.alterarClienteControle(modeloCliente)==true) {
			JOptionPane.showMessageDialog(this,"Produto Alterado com sucesso!");
		}else{
			JOptionPane.showMessageDialog(this,"Eroo ao Alterar produto!");
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisaoCliente frame = new VisaoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
