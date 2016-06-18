package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlElaborarQuestao;
import controle.CtrlPrograma;
import javax.swing.JTextField;

public class JanelaElaborarQuestao extends JFrame {

	private JPanel contentPane;
	private CtrlElaborarQuestao ctrl;
	private JTextField tfQue;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaElaborarQuestao(CtrlElaborarQuestao c) {
		this.ctrl = c;
		setTitle("Questão");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQuesto = new JLabel("Quest\u00E3o:");
		lblQuesto.setBounds(23, 0, 79, 43);
		contentPane.add(lblQuesto);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(107, 56, 280, 236);
		contentPane.add(textArea);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(107, 314, 89, 23);
		contentPane.add(btnIncluir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(323, 314, 89, 23);
		contentPane.add(btnCancelar);
		
		tfQue = new JTextField();
		tfQue.setBounds(107, 11, 261, 20);
		contentPane.add(tfQue);
		tfQue.setColumns(10);
		
		JLabel lblDesc = new JLabel("Descri\u00E7\u00E3o:");
		lblDesc.setBounds(41, 61, 61, 14);
		contentPane.add(lblDesc);
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
