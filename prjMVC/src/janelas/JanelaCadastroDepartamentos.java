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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controle.ControleException;
import controle.CtrlManterDepartamentos;
import controle.ITabelavel;
import dados.DadosException;

public class JanelaCadastroDepartamentos extends JFrame {
	/**
	 * Referência para o controlador do caso de uso
	 * Manter Departamentos
	 */
	private CtrlManterDepartamentos ctrl;

	private JPanel contentPane;
	private JTable table;

	
	/**
	 * Create the frame.
	 */
	public JanelaCadastroDepartamentos(CtrlManterDepartamentos c) {
		this.ctrl = c;
		setTitle("Departamentos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 419, 300);
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
		btnIncluir.setBounds(10, 232, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarExcluir();
			}
		});
		btnExcluir.setBounds(109, 232, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarAlterar();
			}
		});
		btnAlterar.setBounds(208, 232, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarTerminar();
			}
		});
		btnSair.setBounds(307, 232, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 386, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sigla", "Nome", "#Emps"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		
		// monitorando o evento através de TableModelListener
		table.getModel().addTableModelListener(new TableModelListener() {
		    public void tableChanged(TableModelEvent e) {
		    	tratarModificacaoNaTabela(e);
		    }
		});
		
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#limpar()
	 */
	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#incluirLinha(controle.Tabelavel)
	 */
	public void incluirLinha(ITabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
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
		}	
		catch(DadosException e) {
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
		}
		catch(DadosException e) {
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
	
	/**
	 * Exemplo de manipulação de alterações no JTable
	 * @param e
	 */
	public void tratarModificacaoNaTabela(TableModelEvent e) {
        if(e.getType() != TableModelEvent.UPDATE)
        	return;
		int linha = e.getFirstRow();
        int coluna = e.getColumn();
 
        TableModel model = (TableModel) e.getSource();
 
        System.out.println("Você alterou a linha " + linha + ", coluna " + coluna);
        System.out.println("Valor da célula alterada: " + model.getValueAt(linha, coluna));
	}
}


////Obtendo à referencia para a 5 coluna da tabela
//TableColumn column = minhaTabela.getColumnModel().getColumn(4);
////Criando o ComboBox
//JComboBox comboSexo = new JComboBox();
////Definindo os valores para o ComboBox
//DefaultComboBoxModel comboModel = new DefaultComboBoxModel(new String[] { "Masculino", "Feminino" });
//comboSexo.setModel(comboModel);
////Associando o ComboBox para a coluna
//column.setCellEditor(new DefaultCellEditor(comboSexo));
