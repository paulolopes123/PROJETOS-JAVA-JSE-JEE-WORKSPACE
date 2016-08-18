package face;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlDisponibilizarProva;
import controle.CtrlExcluirProva;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaDisponibilizarProva extends JFrame {

	private JPanel contentPane;
	private CtrlDisponibilizarProva ctrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaDisponibilizarProva(CtrlDisponibilizarProva c) {
		this.ctrl = c;
		setTitle("Disponibilizar Prova");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelePr = new JLabel("Selecione a Prova:");
		lblSelePr.setBounds(10, 32, 122, 14);
		contentPane.add(lblSelePr);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 29, 183, 20);
		contentPane.add(comboBox);

		JButton btnDispo = new JButton("Disponibilizar");
		btnDispo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnDispo.setBounds(10, 82, 144, 23);
		contentPane.add(btnDispo);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				executarCancelar();
			}
		});
		btnCancelar.setBounds(43, 134, 89, 23);
		contentPane.add(btnCancelar);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(348, 48, 255, 244);
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
