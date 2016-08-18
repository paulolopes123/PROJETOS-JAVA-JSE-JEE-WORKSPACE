package face;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlConsultarProva;

public class JanelaConsultarProva extends JFrame {

	private JPanel contentPane;
	private CtrlConsultarProva ctrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaConsultarProva(CtrlConsultarProva c) {
		this.ctrl = c;
		setTitle("Consultar Prova");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Selecione a Prova:");
		lblNewLabel.setBounds(10, 11, 200, 14);
		contentPane.add(lblNewLabel);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(363, 39, 271, 308);
		contentPane.add(textArea);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(173, 8, 200, 20);
		contentPane.add(comboBox);

		JButton btnCorri = new JButton("OK");
		btnCorri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();

			}
		});
		btnCorri.setBounds(144, 324, 89, 23);
		contentPane.add(btnCorri);

		List list = new List();
		list.setBounds(247, 39, 110, 308);
		contentPane.add(list);
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
