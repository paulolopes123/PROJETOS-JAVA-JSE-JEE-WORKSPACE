package face;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlAlterarProva;
import controle.CtrlElaborarProva;
import dominio.DadosException;
import dominio.Prova;

public class JanelaAlterarProva extends JFrame {
	//
	// CONSTANTES
	//
	public final static Object SELECAO_NULA = "Selecione...";
	private JPanel contentPane;
	private CtrlAlterarProva ctrl;
	JComboBox cbProvas;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaAlterarProva(CtrlAlterarProva c, Collection<Prova> provas) {

		this.ctrl = c;
		setTitle("Alterar Prova");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelecioneAProva = new JLabel("Selecione a Prova:");
		lblSelecioneAProva.setBounds(10, 11, 141, 14);
		contentPane.add(lblSelecioneAProva);

		cbProvas = new JComboBox();
		cbProvas.setBounds(120, 8, 168, 20);
		contentPane.add(cbProvas);

		//
		// Adicionando os provas na combobox
		//
		cbProvas.addItem(SELECAO_NULA);
		for (Object o : provas)
			if (o != null)
				cbProvas.addItem(o);
		
	
		List list = new List();
		list.setBounds(291, 45, 48, 253);
		contentPane.add(list);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(367, 43, 257, 255);
		contentPane.add(textArea);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(10, 57, 89, 23);
		contentPane.add(btnOk);

		JButton btnIncluirQuesto = new JButton("Incluir Quest\u00E3o");
		btnIncluirQuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					executarIncluir();
				} catch (DadosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnIncluirQuesto.setBounds(10, 110, 141, 23);
		contentPane.add(btnIncluirQuesto);

		JButton btnExcluirQuesto = new JButton("Excluir Quest\u00E3o");
		btnExcluirQuesto.setBounds(10, 165, 141, 23);
		contentPane.add(btnExcluirQuesto);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executarCancelar();
			}
		});
		btnCancelar.setBounds(114, 57, 89, 23);
		contentPane.add(btnCancelar);
		this.setVisible(true);
	}

	public void executarIncluir() throws DadosException {
		try {
			ctrl.incluirQuestao();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void executarCancelar() {
		try {
			ctrl.terminar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}
