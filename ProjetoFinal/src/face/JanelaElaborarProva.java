package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlElaborarProva;
import controle.CtrlLogin;
import controle.CtrlPrograma;
import dominio.DadosException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;

public class JanelaElaborarProva extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextArea taTextoHtml;
	private CtrlElaborarProva ctrl;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaElaborarProva(CtrlElaborarProva c) {
		this.ctrl = c;
		setTitle("Elaborar Prova");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnincluquest = new JButton("Incluir Quest\u00E3o Na Prova");
		btnincluquest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					executarIncluir();
				} catch (DadosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnincluquest.setBounds(294, 22, 190, 23);
		contentPane.add(btnincluquest);

		JLabel lblProva = new JLabel("Prova:");
		lblProva.setBounds(37, 26, 46, 14);
		contentPane.add(lblProva);

		tfNome = new JTextField();
		tfNome.setBounds(79, 23, 190, 30);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		taTextoHtml = new JTextArea();
		taTextoHtml.setBounds(152, 56, 272, 276);
		contentPane.add(taTextoHtml);

		JButton btnelapr = new JButton("OK");
		btnelapr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnelapr.setBounds(80, 377, 89, 23);
		contentPane.add(btnelapr);

		JButton btncancelab = new JButton("Cancelar");
		btncancelab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();
			}
		});
		btncancelab.setBounds(262, 377, 89, 23);
		contentPane.add(btncancelab);

		List list = new List();
		list.setBounds(74, 59, 46, 273);
		contentPane.add(list);
		this.setVisible(true);
	}

	public void executarOk(){
		// Recupero os valores digitados nos textfields
		String nome = tfNome.getText();
		String textoHtml = taTextoHtml.getText();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			
				ctrl.incluir(nome,textoHtml);
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
			ctrl.terminar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void executarIncluir() throws DadosException {
		try {
			ctrl.incluirQuestao();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

}
