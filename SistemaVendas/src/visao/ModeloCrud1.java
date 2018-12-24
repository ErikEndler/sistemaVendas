package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import modelo.ModeloTabela;

public abstract class ModeloCrud1 extends JanelaBaseModelo {
	
	private JPanel componentePainel;
	private JTabbedPane tabbedPane;
	private JPanel painelLocalizar;
	private JComboBox comboBox;
	private JButton btnBuscar;
	private JTextField textFieldCampoBusca;
	private JTable tabelaDados;
	private JPanel painelDados;
	private JLabel lblTituloJanela;
	private JButton btnSalvar;
	private JToolBar toolBar;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnLocalizar;
	private String nomeJanela;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	private int tipoTabela;
	private int tipoSalvar;
	MaskFormatter mascaraCep = null;
    MaskFormatter mascaraTel = null;
    MaskFormatter mascaraCpf = null;
    MaskFormatter mascaraData = null;
    MaskFormatter mascaraMoeda = null;	
	
	public ModeloCrud1() {
		super();
		setTitle("Janela Modelo");
		getLblBarraDeEstatus().setText("Barra de Estatus - Teste 2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init();
	}
	
	public static void main(String[] args) {
		/*ModeloCrud1 frame = new ModeloCrud1();
		frame.setVisible(true);*/

	}

	void init(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mascaras();
		setTipoTabela(0);
		getPainelPrincipal().setLayout(new BorderLayout(0, 0));

		componentePainel = new JPanel();
		getPainelPrincipal().add(componentePainel, BorderLayout.CENTER);
		componentePainel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		componentePainel.add(tabbedPane);
		
		painelLocalizar = new JPanel();
		tabbedPane.addTab("Localizar", null, painelLocalizar, null);
		painelLocalizar.setLayout(new BorderLayout(0, 0));
		
		JPanel painelTopoLocalizar = new JPanel();
		painelLocalizar.add(painelTopoLocalizar, BorderLayout.NORTH);
		painelTopoLocalizar.setLayout(new BorderLayout(0, 0));
		
		JPanel painelTopoLocalizarEsquerda = new JPanel();
		painelTopoLocalizar.add(painelTopoLocalizarEsquerda, BorderLayout.WEST);
		painelTopoLocalizarEsquerda.setLayout(new BoxLayout(painelTopoLocalizarEsquerda, BoxLayout.Y_AXIS));
		
		JLabel lblCampo = new JLabel("Campo a procurar");
		painelTopoLocalizarEsquerda.add(lblCampo);
		
		comboBox = new JComboBox();
		painelTopoLocalizarEsquerda.add(comboBox);
		
		JPanel painelTopoLocalizarDireita = new JPanel();
		painelTopoLocalizarDireita.setBorder(new EmptyBorder(5, 5, 0, 5));
		painelTopoLocalizar.add(painelTopoLocalizarDireita, BorderLayout.EAST);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionBuscar();
			}
		});
		//btnBuscar.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/Zoom.png")));
		painelTopoLocalizarDireita.add(btnBuscar);
		
		JPanel panel = new JPanel();
		painelTopoLocalizar.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblValorAProcurar = new JLabel("Valor a procurar");
		panel.add(lblValorAProcurar);
		
		textFieldCampoBusca = new JTextField();
		panel.add(textFieldCampoBusca);
		textFieldCampoBusca.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		painelLocalizar.add(scrollPane, BorderLayout.CENTER);
		
		tabelaDados = new JTable();
		scrollPane.setViewportView(tabelaDados);
		//painelLocalizar.add(tabelaDados, BorderLayout.CENTER);
		
		
		painelDados = new JPanel();
		tabbedPane.addTab("Dados", null, painelDados, null);

		JPanel painelTopo = new JPanel();
		painelTopo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		getPainelPrincipal().add(painelTopo, BorderLayout.NORTH);

		lblTituloJanela = new JLabel("T\u00EDtulo da Janela");
		lblTituloJanela.setFont(new Font("Tahoma", Font.BOLD, 16));
		painelTopo.add(lblTituloJanela);

		JPanel panielToobar = new JPanel();
		panielToobar.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		getPainelPrincipal().add(panielToobar, BorderLayout.WEST);
		FlowLayout fl_panielToobar = new FlowLayout(FlowLayout.LEADING, 0, 0);
		fl_panielToobar.setAlignOnBaseline(true);
		panielToobar.setLayout(fl_panielToobar);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		panielToobar.add(toolBar);

		btnNovo = new JButton("Novo");
		toolBar.add(btnNovo);
		btnNovo.setMnemonic('N');
		btnNovo.setIcon(new ImageIcon(ModeloCrud1.class.getResource("/icons/Add.png")));
		btnNovo.setVerticalAlignment(SwingConstants.TOP);
		btnNovo.setPreferredSize(new Dimension(60,45));


		btnEditar = new JButton("Editar");
		toolBar.add(btnEditar);
		btnEditar.setMnemonic('E');
		btnEditar.setIcon(new ImageIcon(ModeloCrud1.class.getResource("/icons/Notes.png")));
		btnEditar.setPreferredSize(new Dimension(60,45));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//actionSalvar();
			}
		});
		btnSalvar.setIcon(new ImageIcon(ModeloCrud1.class.getResource("/icons/Save.png")));
		toolBar.add(btnSalvar);
		
		btnExcluir = new JButton("Excluir");
		toolBar.add(btnExcluir);
		btnExcluir.setMnemonic('E');
		btnExcluir.setIcon(new ImageIcon(ModeloCrud1.class.getResource("/icons/Delete.png")));
		btnExcluir.setPreferredSize(new Dimension(60,45));
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.setIcon(new ImageIcon(ModeloCrud1.class.getResource("/icons/View.png")));
		toolBar.add(btnLocalizar);


		toolBar.addSeparator();
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		getMnPrincipal().add(mntmNovo);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		getMnPrincipal().add(mntmEditar);
		
		JMenuItem mntmLocalizar = new JMenuItem("Localizar");
		getMnPrincipal().add(mntmLocalizar);
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JLabel getLblTituloJanela() {
		return lblTituloJanela;
	}
	public JPanel getComponentePainel() {
		return componentePainel;
	}
	public JToolBar getToolBar() {
		return toolBar;
	}
	public JButton getBtnNovo() {
		return btnNovo;
	}
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public JButton getBtnLocalizar() {
		return btnLocalizar;
	}
	public JButton getBtnExcluir() {
		return btnExcluir;
	}
	protected JComboBox getComboBoxCampos() {
		return comboBox;
	}
	protected JTextField getTextFieldCampoBusca() {
		return textFieldCampoBusca;
	}
	public JTable getTabelaDados() {
		return tabelaDados;
	}
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	protected JPanel getPainelDados() {
		return painelDados;
	}
	protected JPanel getPainelLocalizar() {
		return painelLocalizar;
	}
	protected JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	protected JButton getBtnSalvar() {
		return btnSalvar;
	}
	public String getNomeJanela() {
		return nomeJanela;
	}
	public void setNomeJanela(String nomeJanela) {
		this.nomeJanela = nomeJanela;
	}
	protected int getTipoTabela() {
		return tipoTabela;
	}
	protected  void setTipoTabela(int i) {
		this.tipoTabela=i;
	}
	public void preencherTabela() {
		String[] Colunas = colunaTAbela();
		ArrayList dados;
		if(tipoTabela==1) {
			dados = linhaTabelaPesquisa();
		}else {
			dados = linhaTabela();
		}
		if(dados!=null && Colunas !=null) {
			ModeloTabela modelo = new ModeloTabela(dados, Colunas);
			getTabelaDados().setModel(modelo);
			getTabelaDados().getTableHeader().setReorderingAllowed(false);
			getTabelaDados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	}
	abstract ArrayList linhaTabela();

	abstract ArrayList linhaTabelaPesquisa();

	abstract String[]  colunaTAbela();

	public int getTipoSalvar() {
		return tipoSalvar;
	}

	public void setTipoSalvar(int tipoSalvar) {
		this.tipoSalvar = tipoSalvar;
	}
	abstract void acaoNovo();
	abstract void acaoSalvar();
	abstract void acaoEditar();
	
	public void Mascaras() {

        try{
               mascaraCep = new MaskFormatter("#####-###");
               mascaraTel = new MaskFormatter("(##)####-####");
               mascaraCpf = new MaskFormatter("#########-##");
               mascaraData = new MaskFormatter("##/##/####");
               mascaraMoeda = new MaskFormatter("R$ ####,##");
               
               mascaraCep.setPlaceholderCharacter('_');
               mascaraTel.setPlaceholderCharacter('_');
               mascaraCpf.setPlaceholderCharacter('_');
               mascaraData.setPlaceholderCharacter('_');
               mascaraMoeda.setPlaceholderCharacter('0');
        }
        catch(ParseException excp) {
               System.err.println("Erro na formatação: " + excp.getMessage());
               System.exit(-1);
        }
	}
}
