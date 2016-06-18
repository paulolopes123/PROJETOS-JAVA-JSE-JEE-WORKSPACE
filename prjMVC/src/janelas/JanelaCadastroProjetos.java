package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ControleException;
import controle.CtrlManterProjetos;
import controle.ITabelavel;
import dados.DadosException;

public class JanelaCadastroProjetos extends JFrame {
	/**
	 * Referência para o controlador do caso de uso
	 * Manter Projetos
	 */
	private CtrlManterProjetos ctrl;
	/** 
	 * Referência para o painel de conteúdo da janela
	 */
	private JPanel contentPane;
	/**
	 * Referência para a JTable de Projetos
	 */
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroProjetos(CtrlManterProjetos c) {
		this.ctrl = c;
		setTitle("Projetos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 583, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarIncluir();
			}
		});
		btnIncluir.setBounds(81, 234, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarExcluir();	
			}
		});
		btnExcluir.setBounds(180, 234, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarAlterar();	
			}
		});
		btnAlterar.setBounds(279, 234, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarTerminar();
			}
		});
		btnSair.setBounds(378, 234, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 533, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Departamento", "#Empregados"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		table.getColumnModel().getColumn(1).setPreferredWidth(269);
		table.getColumnModel().getColumn(2).setPreferredWidth(247);
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarIncluir()
	 */
	public void executarIncluir() {
		try {
			this.ctrl.iniciarIncluir();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarExcluir()
	 */
	public void executarExcluir() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclusão
		try {
			ctrl.iniciarExcluir(pos);
		} catch(ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}	
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarAlterar()
	 */
	public void executarAlterar() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de alteração
		try {
			ctrl.iniciarAlterar(pos);
		} catch(ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}	
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarTerminar()
	 */
	public void executarTerminar() {
		try {
			ctrl.terminar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see janelas.ICadastroProjetos#limpar()
	 */
	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see janelas.ICadastroProjetos#incluirLinha(controle.Tabelavel)
	 */
	public void incluirLinha(ITabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
	}
}