package face;

import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlIniciarProva;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaIniciarProva extends JFrame {

	private JPanel contentPane;
	private CtrlIniciarProva ctrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaIniciarProva(CtrlIniciarProva c) {
		this.ctrl = c;
		setTitle("Prova");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(95, 53, 289, 235);
		contentPane.add(textArea);

		JLabel lblProva = new JLabel("Prova:");
		lblProva.setBounds(95, 11, 63, 14);
		contentPane.add(lblProva);

		List list_1 = new List();
		list_1.setBounds(10, 53, 63, 235);
		contentPane.add(list_1);

		JButton btnFinPr = new JButton("Finalizar Prova");
		btnFinPr.setBounds(339, 319, 157, 23);
		contentPane.add(btnFinPr);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(156, 8, 228, 20);
		contentPane.add(comboBox);

		JButton btnIniciarProva = new JButton("Iniciar Prova");
		btnIniciarProva.setBounds(172, 319, 136, 23);
		contentPane.add(btnIniciarProva);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executarCancelar();
			}
		});
		btnCancelar.setBounds(37, 319, 89, 23);
		contentPane.add(btnCancelar);
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
