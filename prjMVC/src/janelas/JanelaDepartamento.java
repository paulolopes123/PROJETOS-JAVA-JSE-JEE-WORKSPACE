package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlManterDepartamentos;
import dados.DadosException;

public class JanelaDepartamento extends JFrame {
	//
	// ATRIBUTOS
	//
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private CtrlManterDepartamentos ctrl;
	/**
	 * Indica se estou fazendo uma opera��o de inclus�o ou altera��o
	 */
	private boolean ehAlteracao;

	private JPanel contentPane;
	private JTextField tfSigla;
	private JTextField tfNome;

	/**
	 * Create the frame.
	 */
	public JanelaDepartamento(CtrlManterDepartamentos ct) {
		this.ctrl = ct;
		setTitle("Departamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSigla = new JLabel("Sigla:");
		lblSigla.setBounds(20, 11, 46, 14);
		contentPane.add(lblSigla);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 48, 46, 14);
		contentPane.add(lblNome);

		tfSigla = new JTextField();
		tfSigla.setBounds(76, 8, 86, 20);
		contentPane.add(tfSigla);
		tfSigla.setColumns(10);

		tfNome = new JTextField();
		tfNome.setBounds(76, 45, 334, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnOk.setBounds(73, 95, 143, 23);
		contentPane.add(btnOk);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(256, 95, 154, 23);
		contentPane.add(btnCancelar);

		this.setVisible(true);
	}

	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String sigla = tfSigla.getText();
		String nome = tfNome.getText();
		// Verifico qual � a opera��o que estou fazendo
		// e notifico ao controlador
		try {
			if (!ehAlteracao)
				ctrl.incluir(sigla, nome);
			else
				ctrl.alterar(sigla, nome);
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
			if (!ehAlteracao)
				ctrl.cancelarIncluir();
			else
				ctrl.cancelarAlterar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see janelas.IDepartamento#atualizarCampos(java.lang.String,
	 * java.lang.String)
	 */
	public void atualizarCampos(String sigla, String nome) {
		this.tfSigla.setText(sigla);
		this.tfNome.setText(nome);
		this.ehAlteracao = true;
	}
}
