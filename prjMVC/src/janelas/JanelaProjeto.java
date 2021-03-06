package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlManterProjetos;
import dados.DadosException;
import dados.Departamento;
import dados.Empregado;

public class JanelaProjeto extends JFrame {
	//
	// CONSTANTES
	//
	public final static Object SELECAO_NULA = "Selecione...";
	//
	// ATRIBUTOS
	//
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private CtrlManterProjetos ctrl;
	/**
	 * Indica se estou fazendo uma opera��o de inclus�o ou 
	 * altera��o
	 */
	private boolean ehAlteracao;
	
	private JPanel 			 contentPane;
	private JTextField 		 tfNome;
	private JComboBox  		 cbDeptos;
	private DefaultListModel lstProjetosModel;
	private JList 	   		 lstProjetos;
	
	/**
	 * Create the frame.
	 */
	public JanelaProjeto(CtrlManterProjetos ct, Collection<Departamento> deptos, Collection<Empregado> emps) {
		this.ctrl = ct;
		setTitle("Projeto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("Nome:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(42, 14, 46, 14);
		contentPane.add(lblCpf);
			
		tfNome = new JTextField();
		tfNome.setBounds(98, 11, 86, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnOk.setBounds(53, 237, 143, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(236, 237, 154, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(8, 45, 80, 14);
		contentPane.add(lblDepartamento);
		
		cbDeptos = new JComboBox();
		cbDeptos.setBounds(98, 42, 292, 20);
		contentPane.add(cbDeptos);
		
		lstProjetosModel = new DefaultListModel<Empregado>();
		lstProjetos = new JList(lstProjetosModel);
		lstProjetos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		lstProjetos.setBounds(98, 82, 279, 123);
		contentPane.add(lstProjetos);
		
		JLabel lbEmpregados = new JLabel("Empregados");
		lbEmpregados.setBounds(8, 83, 80, 14);
		contentPane.add(lbEmpregados);
		//
		// Adicionando os deptos na combobox
		//
		cbDeptos.addItem(SELECAO_NULA);
		for(Object o : deptos)
			if(o != null)
				cbDeptos.addItem(o);
		//
		// Adicionando os Empregados na List
		//
		for(Object o : emps)
			if(o != null)
				this.lstProjetosModel.addElement(o);
		this.setVisible(true);
	}

	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String nome = tfNome.getText();
		Object selecionado = cbDeptos.getSelectedItem();
		Departamento depto = selecionado == SELECAO_NULA ? null : (Departamento)selecionado;
		List<Empregado> emps = lstProjetos.getSelectedValuesList();
		// Verifico qual � a opera��o que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteracao)
				ctrl.incluir(nome, depto, emps);
			else
				ctrl.alterar(nome, depto, emps);
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void executarCancelar() {
		try {
			if(!ehAlteracao)
				ctrl.cancelarIncluir();
			else
				ctrl.cancelarAlterar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void atualizarCampos(String nome, Departamento depto, Collection<Empregado> emps) {
		this.tfNome.setText(nome);
		if(depto != null)
			this.cbDeptos.setSelectedItem(depto);
		else
			this.cbDeptos.setSelectedItem(SELECAO_NULA);
		this.lstProjetos.clearSelection();
		for(int i = 0; i < this.lstProjetosModel.getSize(); i++) {
			if(emps.contains(this.lstProjetosModel.elementAt(i)))
	            this.lstProjetos.addSelectionInterval(i, i);			
		}
		this.ehAlteracao = true;
	}
}
