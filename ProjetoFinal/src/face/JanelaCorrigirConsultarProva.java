package face;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlCorrigirConsultarProva;
import controle.CtrlPrograma;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;

public class JanelaCorrigirConsultarProva extends JFrame {

	private JPanel contentPane;
	private CtrlCorrigirConsultarProva ctrl;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public JanelaCorrigirConsultarProva(CtrlCorrigirConsultarProva c) {
		this.ctrl = c;
		setTitle("Corrigir Prova");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecione a Prova:");
		lblNewLabel.setBounds(10, 55, 200, 14);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(363, 94, 271, 253);
		contentPane.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(173, 52, 200, 20);
		contentPane.add(comboBox);
		
		JButton btnCorri = new JButton("Corrigir");
		btnCorri.setBounds(299, 377, 89, 23);
		contentPane.add(btnCorri);
		
		JButton btnCance = new JButton("Cancelar");
		btnCance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCance.setBounds(470, 377, 89, 23);
		contentPane.add(btnCance);
		
		List list = new List();
		list.setBounds(247, 94, 110, 253);
		contentPane.add(list);
		
		JLabel lblSelecioneOAluno = new JLabel("Selecione o Aluno:");
		lblSelecioneOAluno.setBounds(10, 11, 110, 14);
		contentPane.add(lblSelecioneOAluno);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(173, 8, 200, 20);
		contentPane.add(comboBox_1);
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
