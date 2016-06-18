package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlLogin;
import dominio.DadosException;

public class JanelaEfetuarLogin extends JFrame {
	// referência para o painel de conteúdo da janela
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**
	 * Referência para o controlador do programa a quem a janela principal irá
	 * sempre mandar a notificação.
	 */
	private CtrlLogin ctrl;

	public JanelaEfetuarLogin(CtrlLogin c) {
		this.ctrl = c;
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(325, 46, 86, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);

		JButton btnlogar = new JButton("Logar");
		btnlogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método acionado quando o botão "Logar"
				// for pressionado (Método de Callback).
				String conta = tfUsuario.getText();
				String senha = new String(pfSenha.getPassword());
				try {
					ctrl.validarContaSenha(conta, senha);
				} catch (ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});

		btnlogar.setBounds(214, 162, 89, 23);
		contentPane.add(btnlogar);

		JLabel lblusu = new JLabel("Usuario:");
		lblusu.setBounds(242, 49, 65, 14);
		contentPane.add(lblusu);

		JLabel lblsen = new JLabel("Senha:");
		lblsen.setBounds(242, 94, 46, 14);
		contentPane.add(lblsen);

		JButton btnsair = new JButton("Sair");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// Método acionado quando o botão "Sair"
					// for pressionado (Método de Callback).
					ctrl.terminar();
				} catch (ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnsair.setBounds(348, 162, 89, 23);
		contentPane.add(btnsair);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(325, 91, 89, 20);
		contentPane.add(pfSenha);
		this.setVisible(true);
	}
}
