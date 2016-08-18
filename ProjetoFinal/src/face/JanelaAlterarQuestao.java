package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlAlterarQuestao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaAlterarQuestao extends JFrame {

	private JPanel contentPane;
	private CtrlAlterarQuestao ctrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaAlterarQuestao(CtrlAlterarQuestao c) {
		this.ctrl = c;
		setTitle("Alterar Questão");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelQue = new JLabel("Selecione a Quest\u00E3o:");
		lblSelQue.setBounds(10, 11, 157, 14);
		contentPane.add(lblSelQue);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(143, 8, 167, 20);
		contentPane.add(comboBox);

		JButton btnAlt = new JButton("Alterar");
		btnAlt.setBounds(65, 103, 89, 23);
		contentPane.add(btnAlt);

		JButton btnCanc = new JButton("Cancelar");
		btnCanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executarCancelar();
			}
		});
		btnCanc.setBounds(65, 186, 89, 23);
		contentPane.add(btnCanc);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(312, 39, 312, 250);
		contentPane.add(textArea);
		this.setVisible(true);
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
